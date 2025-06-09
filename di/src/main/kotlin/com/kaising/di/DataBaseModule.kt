package com.kaising.di

import android.content.Context
import androidx.room.Room
import com.kaising.data.repository.AppDatabase
import com.kaising.data.repository.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "task_db"
    ).build()

    @Provides
    fun provideTaskDao(db: AppDatabase): TaskDao = db.taskDao()
}