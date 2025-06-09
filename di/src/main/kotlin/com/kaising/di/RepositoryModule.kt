package com.kaising.di

import com.kaising.data.repository.FakeTaskRepository
import com.kaising.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindTaskRepository(taskRepository: FakeTaskRepository): TaskRepository
}