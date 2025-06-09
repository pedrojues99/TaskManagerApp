package com.kaising.data.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "tasks")
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val isDone: Boolean = false,
    val description: String? = null
)