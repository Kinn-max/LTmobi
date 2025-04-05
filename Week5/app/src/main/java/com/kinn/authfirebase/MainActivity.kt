package com.kinn.authfirebase

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.kinn.authfirebase.ui.screen.AuthViewModel
import com.kinn.authfirebase.ui.screen.HomeScreen
import com.kinn.authfirebase.ui.screen.ProfileScreen

class MainActivity : ComponentActivity() {

    private val db = FirebaseFirestore.getInstance()  // Khai báo db Firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        getFurnitureData() // Gọi hàm lấy dữ liệu Firestore

        setContent {
            val navController = rememberNavController()
            val viewModel: AuthViewModel = viewModel()

            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(viewModel = viewModel) {
                        navController.navigate("profile")
                    }
                }
                composable("profile") {
                    ProfileScreen(
                        viewModel = viewModel,
                        navController = navController,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }

    private fun getFurnitureData() {
        db.collection("products")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Firestore", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Firestore", "Lỗi khi lấy dữ liệu.", exception)
            }
    }
}
