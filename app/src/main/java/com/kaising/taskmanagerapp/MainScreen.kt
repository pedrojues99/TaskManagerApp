package com.kaising.taskmanagerapp


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaising.commonui.components.MainCard
import com.kaising.commonui.components.SwipeableCard

@Composable
fun TaskScreen(
    modifier: Modifier,
    viewModel: TaskViewModel = hiltViewModel<TaskViewModel>()
) {
    val tasks = viewModel.tasks.collectAsState()

    LazyColumn(modifier) {
        items(
            count = tasks.value.size,
            key = { task -> task }
        ) {

            SwipeableCard(
                task = tasks.value[it],
                onSwipeLeft = { task -> viewModel.modifyTask(task) },
                onSwipeRight = { task -> viewModel.deleteTask(task) }
                ) {
                MainCard(task = tasks.value[it], modifyTask = { task -> viewModel.modifyTask(task) })
            }
        }
    }
}