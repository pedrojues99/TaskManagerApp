package com.kaising.domain.usecase

import com.kaising.domain.model.Task
import com.kaising.domain.repository.TaskRepository

class GetTasksUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(): List<Task> {
        return repository.getTasks()
    }
}