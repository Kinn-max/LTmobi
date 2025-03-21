package com.example.nodemanager.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.nodemanager.MainViewModel
import com.example.nodemanager.Screen
import com.example.nodemanager.common.enum.LoadStatus
import com.example.nodemanager.ui.screens.login.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel, mainViewModel:MainViewModel) {
    val  state by viewModel.uiState.collectAsState()
    val screenWith = LocalConfiguration.current.screenWidthDp
    LaunchedEffect(Unit) {
        viewModel.loadNotes()
    }
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Note Manager") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {}){
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
             if(state.status is LoadStatus.Loading){
                 Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                     CircularProgressIndicator()
                 }
             }else{
                 if(state.status is LoadStatus.Error){
                     mainViewModel.setError(state.status.description)
                     viewModel.reset()
                 }
                 LazyColumn(modifier = Modifier.padding(16.dp)) {
                        items(state.notes.size){
                            item->
                            ListItem(
                                modifier = Modifier.clickable {
                                   navController.navigate("${Screen.Detail.route}?noteIndex=${item}")
                                },
                                overlineContent = { Text(text = state.notes[item].dateTime.toString()) },
                                headlineContent = {Text(text = state.notes[item].title)},
                                supportingContent = {Text(text = state.notes[item].content)},
                                trailingContent = {
                                    IconButton(onClick = {viewModel.deleteNote(state.notes[item].dateTime)}) {
                                        Icon(Icons.Default.Delete, contentDescription = "")
                                    }
                                }
                            )
                        }
                 }
             }
        }
    }
}
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = NavHostController(LocalContext.current),
        viewModel = HomeViewModel(null,null),
        mainViewModel = MainViewModel()
    )
}
