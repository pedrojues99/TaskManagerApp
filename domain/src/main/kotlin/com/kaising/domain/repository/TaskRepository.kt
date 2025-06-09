package com.kaising.domain.repository

import com.kaising.domain.model.Task

interface TaskRepository {
    fun getTasks(): List<Task>
    suspend fun addTask(task: Task): Boolean
    fun modifyTask(task: Task): Boolean
}