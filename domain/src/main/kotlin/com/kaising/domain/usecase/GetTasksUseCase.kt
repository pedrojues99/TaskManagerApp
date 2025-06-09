package com.kaising.domain.usecase


import com.kaising.domain.model.Task
import com.kaising.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    operator fun invoke(): Flow<List<Task>> = flow {
        emit(repository.getTasks())
    }
}