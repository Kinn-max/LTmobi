package com.example.furniturestore

import android.widget.Toast
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.furniturestore.ui.screens.HomeScreen
import com.example.furniturestore.ui.screens.HomeViewModel
import com.example.furniturestore.ui.screens.detail.DetailScreen


sealed class Screen(val route:String){
    object Home:Screen("home")
    object Detail:Screen("detail")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = hiltViewModel()
    val mainState = mainViewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(mainState.value.error) {
        if (mainState.value.error != null && mainState.value.error != "") {
            Toast.makeText(context, mainState.value.error, Toast.LENGTH_LONG).show()
            mainViewModel.setError("")
        }
    }

    val navBackStackEntry = navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry.value?.destination?.route ?: Screen.Home.route // Giá trị mặc định là Home



    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,

    ) {
        composable(Screen.Home.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                navController = navController,
                viewModel = homeViewModel,
                mainViewModel = mainViewModel
            )
        }
        composable(
            route = Screen.Detail.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val ticketId = backStackEntry.arguments?.getInt("id") ?: -1
            if (ticketId != -1) {
                val homeViewModel: HomeViewModel = hiltViewModel()
                DetailScreen(
                    navController = navController,
                    id = ticketId,
                    homeViewModel,
                    mainViewModel = mainViewModel,
                )
            }
        }

    }
}