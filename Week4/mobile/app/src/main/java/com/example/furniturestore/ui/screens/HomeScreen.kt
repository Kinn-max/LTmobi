package com.example.furniturestore.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.furniturestore.MainViewModel
import com.example.furniturestore.R
import com.example.furniturestore.common.enum.LoadStatus
import com.example.furniturestore.model.Task
import com.example.furniturestore.ui.theme.FurnitureStoreTheme


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    val navController = rememberNavController()
    val homeViewModel = HomeViewModel()
    val mainViewModel = MainViewModel() 

    FurnitureStoreTheme { 
        HomeScreen(
            navController = navController,
            viewModel = homeViewModel,
            mainViewModel = mainViewModel
        )
    }
}

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel,
    mainViewModel: MainViewModel
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadHomeApp()
    }
    val taskList: List<Task> = state.mainResponse?.data ?: emptyList()

    Scaffold (
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .height(56.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = Color(0xFF2196F3),
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {  }
                            .padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "List",
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
                if(taskList.isEmpty() ){
                    Column( modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)) {
                        Empty()
                    }
                }else{
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .background(Color.White)
                            .padding(16.dp)
                    ) {
                        items(taskList) { item ->
                            TaskItem(item, navController)
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                    }
                }
            } else ->{
                Column( modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)) {
                        Empty()
                    }
                }
            }

    }
}

@Composable
fun TaskItem(task:Task, navController: NavHostController) {
    val priorityColor = when (task.priority) {
        "High" -> Color(0xFFFFCDD2)
        "Medium" -> Color(0xFFFFF9C4)
        "Low" -> Color(0xFFC8E6C9)
        else -> Color.White
    }
    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable {
                navController.navigate("detail?id=${task.id}")
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(priorityColor)
                .padding(16.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(8f)
            ) {
                Text(
                    text = "${task.title}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = "${task.description}",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
            Box(
                modifier = Modifier
                    .weight(2f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black)
                    .padding( 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = "Next",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

        }
    }

}
@Composable
fun Empty() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "No tasks",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "No Tasks Yet!",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )

            Text(
                text = "Stay productive—add something to do",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
