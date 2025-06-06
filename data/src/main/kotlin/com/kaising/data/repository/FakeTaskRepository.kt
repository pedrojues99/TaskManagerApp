package com.kaising.data.repository

import com.kaising.domain.model.Task
import com.kaising.domain.repository.TaskRepository

class FakeTaskRepository : TaskRepository {
    override suspend fun getTasks(): List<Task> {
        return listOf(
            Task(1, "Estudiar Clean Architecture", false),
            Task(2, "Practicar Hilt", true),
            Task(3, "Hacer pruebas unitarias", false)
        )
    }
}