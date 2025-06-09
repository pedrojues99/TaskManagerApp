package com.kaising.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>
    @Insert
    suspend fun insertTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)
}