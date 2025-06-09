package com.kaising.domain.usecase

import com.kaising.domain.model.Task
import com.kaising.domain.repository.TaskRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) {
        repository.addTask(task)
    }

}