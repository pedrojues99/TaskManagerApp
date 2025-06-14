package com.kaising.taskmanagerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaising.domain.model.Task
import com.kaising.commonui.theme.TaskManagerAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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