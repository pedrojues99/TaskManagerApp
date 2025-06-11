package com.kaising.taskmanagerapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import com.kaising.taskmanagerapp.ui.theme.TaskManagerAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity @Inject constructor() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TaskManagerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TaskScreen(modifier = Modifier.padding(innerPadding))
                        DraggableText()
                    }
                }
            }
        }
    }
}

@Composable
private fun TaskScreen(
    modifier: Modifier,
    viewModel: TaskViewModel = hiltViewModel<TaskViewModel>()
) {
    val tasks = viewModel.tasks.collectAsState()


    LazyColumn(modifier) {
        items(
            count = tasks.value.size,
            key = { task -> task }
        ) {
            SwipeableListItemWithBackgroundButtons(
                task = tasks.value[it],
                modifyTask = { task -> viewModel.modifyTask(task) },
                onArchive = { task -> viewModel.archiveTask(task) },
                onDelete = { task -> viewModel.deleteTask(task) })
        }

    }
}


@Composable
fun SwipeableListItemWithBackgroundButtons(
    task: Task,
    modifyTask: (Task) -> Unit,
    onArchive: (Task) -> Unit,
    onDelete: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    val dismissState = rememberSwipeToDismissBoxState(
        positionalThreshold = { distance: Float -> distance * 2 },
        confirmValueChange = { targetValue ->
            when (targetValue) {
                SwipeToDismissBoxValue.StartToEnd -> { // Swipe a la Derecha (Archivar)
                    onArchive(task)
                    true // Permitir el dismiss
                }

                SwipeToDismissBoxValue.EndToStart -> { // Swipe a la Izquierda (Borrar)
                    onDelete(task)
                    true // Permitir el dismiss
                }

                SwipeToDismissBoxValue.Settled -> {
                    false
                }
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        modifier = modifier.padding(vertical = 16.dp),
        enableDismissFromStartToEnd = true,
        enableDismissFromEndToStart = true,
        backgroundContent = {
            val direction = dismissState.dismissDirection

            val (backgroundColor, boxArrangement, icon) = when (direction) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    Triple(
                        Color.Green.copy(alpha = 0.7f),
                        Arrangement.Start,
                        Icons.Default.Favorite
                    )
                }

                SwipeToDismissBoxValue.EndToStart -> {
                    Triple(Color.Red.copy(alpha = 0.7f), Arrangement.End, Icons.Default.Clear)
                }

                else -> Triple(Color.Transparent, Arrangement.Center, null)

            }
            Row(Modifier.fillMaxSize(), horizontalArrangement = boxArrangement) {
                Box(
                    Modifier
                        .size(80.dp)
                        .background(backgroundColor)
                        .padding(vertical = 16.dp), Alignment.Center
                ) {
                    icon?.let {
                        Icon(icon, "Icon")
                    }
                }
            }

        }
    ) {
        var isChecked by remember { mutableStateOf(task.isCompleted) }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(80.dp) // Asumiendo la altura de tu Card
        ) {
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = task.title,
                        color = MaterialTheme.colorScheme.onSurface
                    ) // Adapta colores a tu tema
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
@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
private fun DraggableText() {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    var offsetX by remember { mutableFloatStateOf(0f) }
    var text by remember { mutableStateOf("Drag me!") }
    var draggableState = rememberDraggableState { delta ->
        offsetX += delta
        text = if (offsetX >= screenWidthDp / 2) {
            "Im on right!!"
        } else if(offsetX <= -screenWidthDp / 2) {
            "Im on left!!"
        } else {
            "Drag me!"
        }
    }
    Text(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .draggable(
                orientation = Orientation.Horizontal,
                state = draggableState
            ),
        text = text
    )
}