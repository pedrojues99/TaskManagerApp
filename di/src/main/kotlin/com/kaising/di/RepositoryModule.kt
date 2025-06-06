package com.kaising.di

import com.kaising.data.repository.FakeTaskRepository
import com.kaising.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTaskRepository(): TaskRepository {
        return FakeTaskRepository()
    }
}