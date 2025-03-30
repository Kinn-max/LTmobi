package com.kinn.authfirebase.ui.screen

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kinn.authfirebase.R

@Composable
fun HomeScreen(
    viewModel: AuthViewModel,
    onSignInSuccess: () -> Unit
) {
    val context = LocalContext.current
    val isSignedIn by viewModel.isSignedIn.collectAsState()
    val user by viewModel.user.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.handleSignInResult(result.data)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.initialize(context)
    }

    LaunchedEffect(isSignedIn) {
        if (isSignedIn) {
            onSignInSuccess()
        }
    }
    if (!isSignedIn) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.img_2),
                    contentDescription = "Background Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .padding(top = 60.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .width(200.dp)
                            .height(195.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "SmartTasks",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2196F3)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "A simple and efficient to-do app",
                        fontSize = 16.sp,
                        color = Color(0xFF3991D8)
                    )
                }
            }

            Spacer(modifier = Modifier.height(180.dp))

            Text(
                text = "Welcome",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF333333)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Ready to explore? Log in to get started.",
                fontSize = 14.sp,
                color = Color(0xFF4A4646)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .clickable { viewModel.signIn(launcher) }
                    .width(250.dp)
                    .height(50.dp)
                    .padding(4.dp)
                    .background(Color(0xFFD5EDFF), shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_1),
                        contentDescription = "Google Icon",
                        modifier = Modifier.size(width = 15.dp, height = 20.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "SIGN IN WITH GOOGLE",
                        color = Color(0xFF130160),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(150.dp))

            Text(
                text = "© UTHSmartTasks",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }


}

