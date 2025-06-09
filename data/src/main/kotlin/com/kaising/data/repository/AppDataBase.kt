package com.kaising.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Inject

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}