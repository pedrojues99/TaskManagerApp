package com.kaising.taskmanagerapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaising.domain.model.Task
import com.kaising.domain.repository.TaskRepository
import com.kaising.domain.usecase.GetTasksUseCase
import com.kaising.domain.usecase.ModifyTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val modifyTaskUseCase: ModifyTaskUseCase
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    init {
        Log.d("TaskViewModel", "?")
        getTasks()
    }

    fun getTasks() {
        viewModelScope.launch {
            getTasksUseCase().collect { taskList ->
                _tasks.value = taskList
            }
        }
    }

    fun modifyTask(task: Task) {
        viewModelScope.launch {
            modifyTaskUseCase(task)
        }
    }
}