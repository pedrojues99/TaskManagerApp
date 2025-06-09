package com.kaising.domain.usecase

import com.kaising.domain.model.Task
import com.kaising.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase@Inject constructor() {
    @Inject
    lateinit var repository: TaskRepository

    suspend operator fun invoke(): Flow<List<Task>> {
        return repository.getTasks()
    }
}