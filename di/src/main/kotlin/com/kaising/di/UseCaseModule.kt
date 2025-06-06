package com.kaising.di

import com.kaising.domain.repository.TaskRepository
import com.kaising.domain.usecase.AddTaskUseCase
import com.kaising.domain.usecase.GetTasksUseCase
import dagger.Module
import dagger.Provides

@Module
object UseCaseModule {

    @Provides
    fun provideGetTasksUseCase(
        repository: TaskRepository
    ): GetTasksUseCase = GetTasksUseCase(repository)

    @Provides
    fun provideAddTaskUseCase(
        repository: TaskRepository
    ): AddTaskUseCase = AddTaskUseCase(repository)

    // y así con los demás casos de uso
}