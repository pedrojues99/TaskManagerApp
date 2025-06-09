package com.kaising.taskmanagerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    Text(modifier = modifier, text = "Hola")
    /*
    LazyColumn(modifier) {

        with(tasks.value) {
            if (isEmpty()) {
                item {
                    Text(text = "No tasks")
                }
            }
            else {
                tasks.value.forEach {
                    item {
                        Text(text = it.toString())
                    }
                }
            }
        }


    }
     */
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