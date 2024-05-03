package com.example.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tasks.databinding.TaskItemBinding

class TaskItemAdapter(val clickListener: (taskId: Long) -> Unit) :
	ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
		return TaskItemViewHolder.inflateFrom(parent)
	}

	override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
		val item = getItem(position)
		holder.bind(item, clickListener)
	}

	class TaskItemViewHolder(private val binding: TaskItemBinding) : ViewHolder(binding.root) {
		fun bind(item: Task, clickListener: (taskId: Long) -> Unit) {
			binding.task = item
			binding.root.setOnClickListener {clickListener(item.taskId)}
		}

		companion object {
			fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
				val layoutInflater = LayoutInflater.from(parent.context)
				val view = layoutInflater.inflate(R.layout.task_item, parent, false) as CardView
				val binding = TaskItemBinding.inflate(layoutInflater, parent, false)
				return TaskItemViewHolder(binding)
			}
		}
	}
}
