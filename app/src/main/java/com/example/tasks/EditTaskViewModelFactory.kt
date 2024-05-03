package com.example.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class EditTaskViewModelFactory(private val taskId: Long, private val dao: TaskDao): ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(EditTaskViewModel::class.java)) {
			return EditTaskViewModel(taskId, dao) as T
		}
		throw IllegalArgumentException("Unknown ViewModel")
	}
}