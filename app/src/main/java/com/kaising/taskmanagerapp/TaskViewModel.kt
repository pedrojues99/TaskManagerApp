package com.kaising.taskmanagerapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaising.domain.model.Task
import com.kaising.domain.repository.TaskRepository
import com.kaising.domain.usecase.AddTaskUseCase
import com.kaising.domain.usecase.DeleteTaskUseCase
import com.kaising.domain.usecase.GetTasksUseCase
import com.kaising.domain.usecase.ModifyTaskUseCase
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

    private val _tasks = MutableStateFlow<List<Task>>(listOf())
    val tasks: StateFlow<List<Task>> = _tasks

    init {
        Log.d("TaskViewModel", "?")
        getTasks()
    }

    fun getTasks() {
        viewModelScope.launch {
            getTasksUseCase().collect { taskList ->
                _tasks.value = taskList.toMutableList()
            }
        }
    }

    fun modifyTask(task: Task) {
        viewModelScope.launch {
            modifyTaskUseCase(task)
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            addTaskUseCase(task)
        }
        _tasks.update { currentTasks ->
            currentTasks + task
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }

        _tasks.update { currentTasks ->
            currentTasks.filterNot { it.id == task.id }
        }
    }

    fun archiveTask(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }

        _tasks.update { currentTasks ->
            currentTasks.filterNot { it.id == task.id }
        }
    }
}