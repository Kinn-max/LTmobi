package com.kinn.ex1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kinn.ex1.config.PreferencesManager
import com.kinn.ex1.ui.theme.Ex1Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferencesManager = PreferencesManager(this)

        setContent {
            Ex1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ThemeSettingScreen(preferencesManager = preferencesManager)
                }
            }
        }
    }
}

@Composable
fun ThemeSettingScreen(preferencesManager: PreferencesManager) {
    val scope = rememberCoroutineScope()
    val backgroundColor by preferencesManager.backgroundColorFlow.collectAsState(initial = Color.White)
    var selectedColor by remember { mutableStateOf(backgroundColor) }

    // Cập nhật selectedColor khi backgroundColor từ Flow thay đổi
    LaunchedEffect(backgroundColor) {
        selectedColor = backgroundColor
    }

    val colorOptions = listOf(
        Color(0xFFFF0000), // đỏ
        Color(0xFF00FF00), // xanh lá
        Color(0xFFFFC1CC)  // hồng
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor), // Sử dụng backgroundColor từ Flow
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Setting",
                color = Color(0xFF2196F3),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Choosing the right theme sets the tone and personality of your app",
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                colorOptions.forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(width = 60.dp, height = 40.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(color)
                            .border(
                                width = if (selectedColor == color) 3.dp else 0.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable {
                                selectedColor = color
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    scope.launch {
                        preferencesManager.saveBackgroundColor(selectedColor)
                        // Không cần cập nhật selectedColor ở đây vì LaunchedEffect sẽ xử lý
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text(text = "Apply", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}