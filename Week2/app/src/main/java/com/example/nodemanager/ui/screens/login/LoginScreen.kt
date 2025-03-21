package com.example.nodemanager.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.nodemanager.MainViewModel
import com.example.nodemanager.Screen
import com.example.nodemanager.common.enum.LoadStatus


@Composable
fun LoginScreen(navController: NavHostController,viewModel: LoginViewModel, mainViewModel:MainViewModel) {
    val  state = viewModel.uiState.collectAsState()
    Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        if(state.value.statusLoad is LoadStatus.Loading){
            CircularProgressIndicator()
        }else if(state.value.statusLoad is LoadStatus.Success){
            LaunchedEffect(Unit) { // only one call
                  navController.navigate(Screen.Home.route)
            }
        }else{
            if (state.value.statusLoad is LoadStatus.Error){
                mainViewModel.setError(state.value.statusLoad.description)
                viewModel.reset()
            }
            OutlinedTextField(value = state.value.username, onValueChange = {
                viewModel.updateUserName(it)
            }, modifier = Modifier.padding(16.dp), label = { Text("User Name") })

            OutlinedTextField(value = state.value.password, onValueChange = {
                viewModel.updatePassword(it)
            }, modifier = Modifier.padding(16.dp), label = { Text("Password") })
            ElevatedButton(onClick = {viewModel.login()},Modifier.padding(16.dp)) {
                Text("Login")
            }
        }
    }
}
@Preview
@Composable
fun LoginScreenPreview() {
    val mockViewModel = LoginViewModel(log = null, api = null)
    val mockMainViewModel = MainViewModel()

    LoginScreen(
        navController = NavHostController(LocalContext.current),
        viewModel = mockViewModel,
        mainViewModel = mockMainViewModel
    )
}
