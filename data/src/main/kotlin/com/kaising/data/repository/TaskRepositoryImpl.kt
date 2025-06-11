package com.kaising.data.repository

import com.kaising.domain.model.Task
import com.kaising.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao
) : TaskRepository {

    override suspend fun getTasks(): List<Task> = dao.getAllTasks().map { it.toTask() }

    override suspend fun addTask(task: Task): Boolean {
        val result = dao.insertTask(task.toTaskEntity())
        return result.toInt() != -1
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task.toTaskEntity())
    }

    override suspend fun modifyTask(task: Task): Boolean {
        val result = dao.insertTask(task.toTaskEntity())
        return result.toInt() != -1
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
