package com.kaising.taskmanagerapp

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaising.domain.model.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlin.concurrent.thread
import kotlin.div
import kotlin.math.roundToInt
import kotlin.text.compareTo
import kotlin.unaryMinus

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

            SwipeableButtons(
                task = tasks.value[it],
                modifyTask = { task -> viewModel.modifyTask(task) },
                onArchive = { task -> viewModel.archiveTask(task) },
                onDelete = { task -> viewModel.deleteTask(task) })
        }


    }
}

@Composable
fun SwipeableButtons(
    task: Task,
    modifyTask: (Task) -> Unit,
    onArchive: (Task) -> Unit,
    onDelete: (Task) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    var x by remember { mutableFloatStateOf(0f) }
    var time by remember { mutableIntStateOf(0) }
    val offsetX by animateDpAsState(
        targetValue = x.dp,
        animationSpec = tween(durationMillis = time)
    )
    var draggableState = rememberDraggableState { delta ->
        x += delta
    }
    suspend fun CoroutineScope.onDragStopped() {
        if (x >= screenWidthDp / 0.8) {
            onArchive(task)
        } else if (x <= -screenWidthDp / 0.8) {
            onDelete(task)
        }
        time = 500
        x = 0f
        delay(500)
        time = 0
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .offset(offsetX)
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = draggableState,
                    onDragStopped = {
                        onDragStopped()
                    }
                )) {

            var isChecked by remember { mutableStateOf(task.isCompleted) }
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = task.title,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    task.description?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                }
                Checkbox(isChecked, onCheckedChange = {
                    isChecked = it
                    modifyTask(task.copy(isCompleted = isChecked))
                })
            }
        }

    }

}

@Composable
fun Prueba() {
    var isMoved by remember { mutableStateOf(true) }
    val offsetX by animateDpAsState(
        targetValue = if (isMoved) 200.dp else 0.dp,
        animationSpec = tween(durationMillis = 2000)
    )

    Box(
        modifier = Modifier
            .offset(x = offsetX)
            .size(100.dp)
            .background(Color.Red)
    )
    Checkbox(isMoved, onCheckedChange = {
        isMoved = it
    })

}