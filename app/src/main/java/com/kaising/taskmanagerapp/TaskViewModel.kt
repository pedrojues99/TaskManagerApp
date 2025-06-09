package com.kaising.taskmanagerapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaising.domain.model.Task
import com.kaising.domain.usecase.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    init {
        viewModelScope.launch {
            getTasksUseCase().collect { taskList ->
                _tasks.value = taskList
            }
        }
    }
}