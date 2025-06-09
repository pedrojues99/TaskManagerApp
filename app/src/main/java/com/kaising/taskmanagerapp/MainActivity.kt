package com.kaising.taskmanagerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaising.taskmanagerapp.ui.theme.TaskManagerAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TaskManagerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaskScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
private fun TaskScreen(modifier: Modifier, viewModel: TaskViewModel = hiltViewModel<TaskViewModel>()) {
    val tasks = viewModel.tasks.collectAsState()

    LazyColumn(modifier) {

        with(tasks.value) {
            if (isEmpty()) {
                item {
                    Text(text = "No tasks")
                }
            }
            else {
                tasks.value.forEach { task ->
                    item {
                        var isChecked by remember { mutableStateOf(task.isCompleted) }
                        Card(modifier = Modifier.fillMaxWidth().height(100.dp).padding(16.dp)) {
                            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                                Column(modifier = Modifier.padding(16.dp).weight(1f)) {
                                    Text(text = task.title, color = Color.White)
                                    task.description?.let {
                                        Text(text = it)
                                    }
                                }
                                Checkbox(isChecked, onCheckedChange = {
                                    isChecked = it
                                    viewModel.modifyTask(task.copy(isCompleted = isChecked))
                                })
                            }
                        }
                    }
                }
            }
        }


    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskManagerAppTheme {
        Greeting("Android")
    }
}