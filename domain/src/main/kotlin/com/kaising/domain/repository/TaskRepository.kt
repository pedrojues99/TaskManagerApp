package com.kaising.domain.repository

import com.kaising.domain.model.Task

interface TaskRepository {
    suspend fun getTasks(): List<Task>
    suspend fun addTask(task: Task): Boolean
    suspend fun deleteTask(task: Task)
    suspend fun modifyTask(task: Task): Boolean
}