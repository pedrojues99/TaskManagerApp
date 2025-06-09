package com.kaising.data.repository

import android.util.Log
import com.kaising.domain.model.Task
import com.kaising.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val dao: TaskDao
) : TaskRepository {

    override fun getTasks(): List<Task> = dao.getAllTasks().map { it.toTask() }


    override suspend fun addTask(task: Task): Boolean {
        val result = dao.insertTask(task.toTaskEntity())
        return result.toInt() != -1
    }

    override fun modifyTask(task: Task): Boolean {
        TODO("Not yet implemented")
    }

    private fun TaskEntity.toTask(): Task {
        return Task(
            id = id,
            title = title,
            isCompleted = isDone,
            description = description
        )
    }


}

private fun Task.toTaskEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        isDone = isCompleted,
        description = description
    )
}
