package com.kaising.domain.repository

import com.kaising.domain.model.Task

interface TaskRepository {
    suspend fun getTasks(): List<Task>
}