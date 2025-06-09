package com.kaising.domain.usecase

import com.kaising.domain.model.Task
import com.kaising.domain.repository.TaskRepository
import javax.inject.Inject

class ModifyTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    operator fun invoke(task: Task) {
        repository.modifyTask(task)
    }
}