package com.example.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.tasks.databinding.FragmentEditTaskBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EditTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditTaskFragment : Fragment() {
	private var _binding: FragmentEditTaskBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentEditTaskBinding.inflate(inflater, container, false)
		val view = binding.root

		val taskId = EditTaskFragmentArgs.fromBundle(requireArguments()).taskId

		val application = requireNotNull(this.activity).application
		val dao = TaskDatabase.getInstance(application).taskDao
		val viewModelFactory = EditTaskViewModelFactory(taskId, dao)
		val viewModel = ViewModelProvider(this, viewModelFactory)[EditTaskViewModel::class.java]
		binding.viewModel = viewModel
		binding.lifecycleOwner = viewLifecycleOwner

		viewModel.navigateToList.observe(viewLifecycleOwner) { navigate ->
			if (navigate) {
				view.findNavController().navigate(R.id.action_editTaskFragment_to_tasksFragment)
				viewModel.onNavigatedToList()
			}
		}

		return view
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}