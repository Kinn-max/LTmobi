package com.example.nodemanager.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import com.example.nodemanager.MainViewModel
import com.example.nodemanager.Screen
import com.example.nodemanager.ui.screens.home.HomeViewModel
import com.example.nodemanager.ui.screens.login.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    viewModel: HomeViewModel,
    mainViewModel: MainViewModel,
    index:Int
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Detail") })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val state = viewModel.uiState.collectAsState()
            if(index >= 0){
                Column {
                    Text("Date time: ${state.value.notes[index].dateTime}")
                    Box(modifier = Modifier.height(10.dp))
                    Text("Date time: ${state.value.notes[index].title}")
                    Box(modifier = Modifier.height(10.dp))
                    Text("Date time: ${state.value.notes[index].content}")
                    ElevatedButton(onClick = {
                        navController.navigate("${Screen.AddOrEdit.route}/${index}")
                    }) { Text("Edit") }
                }
            }
        }
    }
}