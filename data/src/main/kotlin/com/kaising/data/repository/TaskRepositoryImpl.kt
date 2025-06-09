package com.kaising.data.repository

import com.kaising.domain.model.Task
import com.kaising.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton



class TaskRepository @Inject constructor(
    private val dao: TaskDao
) : TaskRepository {

    override suspend fun getTasks(): Flow<List<Task>> {
        return dao.getAllTasks().map { entities -> entities.map { it.toTask() } }
    }

    override suspend fun addTask(task: Task): Boolean {
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