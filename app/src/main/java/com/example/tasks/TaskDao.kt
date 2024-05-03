package com.example.tasks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(task: Task)

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insertAll(task: List<Task>)

	@Update
	suspend fun update(task: Task)

	@Delete
	suspend fun delete(task: Task)

	@Query("SELECT * FROM task_table WHERE taskId = :taskId")
	fun get(taskId: Long): LiveData<Task>

	@Query("SELECT * FROM task_table ORDER BY taskId DESC")
	fun getAll(): LiveData<List<Task>>
}