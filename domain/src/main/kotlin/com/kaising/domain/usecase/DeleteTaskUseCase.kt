package com.kaising.domain.usecase


import com.kaising.domain.model.Task
import com.kaising.domain.model.TaskState
import com.kaising.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) {
        repository.deleteTask(task.copy(state = TaskState.DELETED))
    }
}