package com.example.furniturestore

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Note
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.furniturestore.ui.screens.HomeScreen
import com.example.furniturestore.ui.screens.HomeViewModel
import com.example.furniturestore.ui.screens.add.DetailScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Add : Screen("add")
    object Detail : Screen("detail")

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
    val currentRoute = navBackStackEntry.value?.destination?.route ?: Screen.Home.route
    val screensWithBottomBar = listOf(Screen.Home.route)

    Scaffold(
        bottomBar = {
            if (currentRoute in screensWithBottomBar) {
                NavigationBar(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        .background(Color.White),
                    tonalElevation = 8.dp
                ) {
                    NavigationBarItem(
                        selected = navController.currentDestination?.route == Screen.Home.route,
                        onClick = { navController.navigate(Screen.Home.route) },
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home",
                                tint = if (navController.currentDestination?.route == Screen.Home.route) Color(0xFF2196F3) else Color.Gray
                            )
                        }
                    )

                    NavigationBarItem(
                        selected = navController.currentDestination?.route == "wishlist",
                        onClick = { navController.navigate("wishlist") },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.CalendarMonth,
                                contentDescription = "Wishlist",
                                tint = if (navController.currentDestination?.route == "wishlist") Color(0xFF2196F3) else Color.Gray
                            )
                        }
                    )

                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(Color(0xFF2196F3), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { navController.navigate("search") }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add",
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }

                    NavigationBarItem(
                        selected = navController.currentDestination?.route == "profile",
                        onClick = { navController.navigate("profile") },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Note,
                                contentDescription = "Profile",
                                tint = if (navController.currentDestination?.route == "profile") Color(0xFF2196F3) else Color.Gray
                            )
                        }
                    )

                    NavigationBarItem(
                        selected = navController.currentDestination?.route == "cart",
                        onClick = { navController.navigate("cart") },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = "Cart",
                                tint = if (navController.currentDestination?.route == "cart") Color(0xFF2196F3) else Color.Gray
                            )
                        }
                    )
                }
            }
        }

    ) { innerPadding ->
        val homeViewModel: HomeViewModel = hiltViewModel()
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navController = navController,
                    viewModel = homeViewModel,
                    mainViewModel = mainViewModel
                )
            }
            composable(Screen.Add.route) {
                DetailScreen(
                    navController = navController,
                    viewModel = homeViewModel,
                    mainViewModel = mainViewModel
                )
            }

        }
    }
}