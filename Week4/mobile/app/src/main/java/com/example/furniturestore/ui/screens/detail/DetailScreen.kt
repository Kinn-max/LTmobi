package com.example.furniturestore.ui.screens.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.furniturestore.MainViewModel
import com.example.furniturestore.common.enum.LoadStatus
import com.example.furniturestore.model.Task
import com.example.furniturestore.ui.screens.Empty
import com.example.furniturestore.ui.screens.HomeViewModel

@Composable
fun DetailScreen(
    navController: NavHostController,
    id: Int,
    viewModel: HomeViewModel,
    mainViewModel: MainViewModel
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadHomeApp()
    }

    val taskList: List<Task> = state.mainResponse?.data ?: emptyList()
    val selectedTask = taskList.find { it.id == id }
    val subtasks = selectedTask?.subtasks
    val attachments = selectedTask?.attachments
    val reminders = selectedTask?.reminders

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .height(56.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = Color(0xFF2196F3),
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                navController.popBackStack()
                            }
                            .padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Detail",
                        color = Color(0xFF2196F3),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            when (state.status) {
                is LoadStatus.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color(0xFF2196F3))
                    }
                }
                is LoadStatus.Success -> {
                    if (selectedTask != null) {
                        Text(
                            text = selectedTask.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = selectedTask.description,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // Subtasks
                        Text(text = "Subtasks", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        subtasks?.forEach { task ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                                    .padding(12.dp)
                            ) {
                                Text(text = task.title, fontSize = 14.sp)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        // Attachments
                        Text(text = "Attachments", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        attachments?.forEach { fileName ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                                    .padding(12.dp)
                            ) {
                                Text(text = fileName.fileName, fontSize = 14.sp)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        // Reminders
                        Text(text = "Reminders", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        reminders?.forEach { reminder ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                                    .padding(12.dp)
                            ) {
                                Text(text = reminder.type, fontSize = 14.sp)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    } else {
                        Text(text = "Task not found", fontSize = 16.sp, color = Color.Red)
                    }
                }
                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Đang tải dữ liệu...",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}
