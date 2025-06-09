package com.kaising.domain.repository

import com.kaising.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun getTasks(): Flow<List<Task>>
    suspend fun addTask(task: Task): Boolean
}