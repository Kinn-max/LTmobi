package com.example.furniturestore.ui.screens.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.furniturestore.MainViewModel
import com.example.furniturestore.R
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
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 10.dp )
                    .height(56.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_4),
                        contentDescription = "App Logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clickable {
                                navController.popBackStack()
                            }
                            .size(40.dp)
                    )

                    Text(
                        text = "Detail",
                        color = Color(0xFF2196F3),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )

                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(Color(0xFFFF9800), Color(0xFFE91E63))
                                )
                            )
                            .clickable { /*  */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
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
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = selectedTask.title,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = selectedTask.description,
                                fontSize = 16.sp,
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth(),
                            )
                            Spacer(modifier = Modifier.height(12.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFFFCDD2), shape = RoundedCornerShape(12.dp)) // Màu hồng nhạt và bo góc
                                    .padding(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.GridView,
                                                contentDescription = "Category",
                                                modifier = Modifier.size(28.dp)
                                            )
                                        }
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(text = "Category", color = Color.Black)
                                            Text(text = selectedTask.category, fontWeight = FontWeight.Bold, color = Color.Black)
                                        }
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Checklist,
                                                contentDescription = "Check",
                                                modifier = Modifier.size(28.dp)
                                            )
                                        }
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(text = "Status", color = Color.Black)
                                            Text(text = selectedTask.status, fontWeight = FontWeight.Bold, color = Color.Black)
                                        }
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.Star,
                                                contentDescription = "Star",
                                                modifier = Modifier.size(28.dp)
                                            )
                                        }
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(text = "Priority", color = Color.Black)
                                            Text(text = selectedTask.priority, fontWeight = FontWeight.Bold, color = Color.Black)
                                        }
                                    }

                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Subtasks
                        Text(text = "Subtasks", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        subtasks?.forEach { task ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                                    .padding(12.dp)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {

                                    Checkbox(
                                        checked = task.isCompleted,
                                        onCheckedChange = {  }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = task.title,
                                        fontSize = 18.sp,
                                        color = Color.DarkGray,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )

                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        // Attachments
                        Text(text = "Attachments", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        attachments?.forEach { fileName ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                                    .padding(12.dp)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {

                                    Icon(
                                        imageVector = Icons.Default.Link,
                                        contentDescription = "Link",
                                        tint = Color.Black,
                                        modifier = Modifier.size(28.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))

                                    Text(
                                        text = fileName.fileName,
                                        fontSize = 18.sp,
                                        color = Color.DarkGray,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )

                                }
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
