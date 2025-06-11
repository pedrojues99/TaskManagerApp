package com.kaising.data.repository

import com.kaising.domain.repository.TaskRepository
import com.kaising.domain.model.Task
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeTaskRepository @Inject constructor() : TaskRepository {
    override suspend fun getTasks(): List<Task> {
        return listOf(
            Task(1, "Estudiar Clean Architecture", false),
            Task(2, "Practicar Hilt", true),
            Task(3, "Hacer pruebas unitarias", false)
        )
    }

    override suspend fun addTask(task: Task): Boolean {
        return true
    }

    override suspend fun deleteTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun modifyTask(task: Task): Boolean {
        return true
    }
}