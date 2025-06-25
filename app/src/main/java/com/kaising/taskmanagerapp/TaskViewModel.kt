package com.kaising.taskmanagerapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaising.commonui.model.TaskUiModel
import com.kaising.domain.usecase.AddTaskUseCase
import com.kaising.domain.usecase.DeleteTaskUseCase
import com.kaising.domain.usecase.GetTasksUseCase
import com.kaising.domain.usecase.ModifyTaskUseCase
import com.kaising.taskmanagerapp.mapper.toDomain
import com.kaising.taskmanagerapp.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val modifyTaskUseCase: ModifyTaskUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<TaskUiModel>>(listOf())
    val tasks: StateFlow<List<TaskUiModel>> = _tasks

    init {
        getTasks()
    }

    fun getTasks() {
        viewModelScope.launch {
            getTasksUseCase()
                .collect { taskList ->
                    _tasks.value = taskList.map { it.toUiModel() }
            }
        }
    }

    fun modifyTask(task: TaskUiModel) {
        viewModelScope.launch {
            modifyTaskUseCase(task.toDomain())
        }
    }

    fun addTask(task: TaskUiModel) {
        viewModelScope.launch {
            addTaskUseCase(task.toDomain())
        }
        _tasks.update { currentTasks ->
            currentTasks + task
        }
    }

    fun deleteTask(task: TaskUiModel) {
        viewModelScope.launch {
            deleteTaskUseCase(task.toDomain())
        }

        _tasks.update { currentTasks ->
            currentTasks.filterNot { it.id == task.id }
        }
    }

    fun archiveTask(task: TaskUiModel) {
        viewModelScope.launch {
            deleteTaskUseCase(task.toDomain())
        }

        _tasks.update { currentTasks ->
            currentTasks.filterNot { it.id == task.id }
        }
    }
}