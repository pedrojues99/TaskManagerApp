package com.kaising.di

import com.kaising.data.repository.TaskRepositoryImpl
import com.kaising.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindTaskRepository(taskRepository: TaskRepositoryImpl): TaskRepository
}