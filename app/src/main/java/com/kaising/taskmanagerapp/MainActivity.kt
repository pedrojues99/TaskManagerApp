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
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Button
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
        var contador = 4
        setContent {
            TaskManagerAppTheme {
                val viewModel = hiltViewModel<TaskViewModel>()
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    Column {
                        Spacer(Modifier.height(40.dp))
                        Button(onClick = { viewModel.addTask(Task(contador,"prueba", true, "prueba"))
                            contador ++
                        }) { Text("Add task") }
                    }
                }) { innerPadding ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TaskScreen(modifier = Modifier.padding(innerPadding), viewModel)
                    }
                }
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