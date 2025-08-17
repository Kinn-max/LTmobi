package com.kinn.tesst.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kinn.tesst.R

@Preview
@Composable
fun HomeScreen(
) {
    val WorkSansSemibold = FontFamily(
        Font(R.font.semibold)
    )
    val WorkSansRegular= FontFamily(
        Font(R.font.worksan_regular)
    )
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .background(Color(0xFFFAF8F8))
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp)){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        modifier = Modifier
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(50)
                            )
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.robot_image),
                            contentDescription = null,
                            tint = Color(0xFF00BD50),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "500+ TOEIC",
                            color = Color(0xFF00BD50),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircleIcon(iconId = R.drawable.ic_search_normal)
                        Spacer(modifier = Modifier.width(8.dp))
                        CircleIcon(iconId = R.drawable.ic_search_normal)
                    }
                }

            }
        },
        bottomBar = {
            BottomNavigationBar()
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color(0xFFFAF8F8))
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                GreetingSection("Trần Chung Kiên")
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFAF8F8))
                        .padding(start = 24.dp, end = 24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Làm bài test",
                            fontFamily = WorkSansSemibold,
                            fontSize = 20.sp,
                            color = Color(0xFF080F05),
                            fontWeight = FontWeight.W700,
                            lineHeight = 28.sp
                        )
                        Text("Xem tất cả",
                            fontFamily = WorkSansRegular,
                            fontSize = 16.sp,
                            color = Color(0xFF807E94),
                            fontWeight = FontWeight.W500,
                            lineHeight = 28.sp
                        )
                    }
                }
            }

//            item {
//                CityCardList()
//            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFAF8F8))
                        .padding(start = 24.dp, end = 24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Luyện nói với AI",
                            fontFamily = WorkSansSemibold,
                            fontSize = 20.sp,
                            color = Color(0xFF080F05),
                            fontWeight = FontWeight.W700,
                            lineHeight = 28.sp
                        )
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp)
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, RoundedCornerShape(12.dp))
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Thời gian học tập",
                                fontFamily = WorkSansSemibold,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W600,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                "You can speak well ",
                                fontFamily = WorkSansSemibold,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W700,
                                color = Color(0xFFCDCDCD)
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                "Học 10 phút với Kinn",
                                fontFamily = WorkSansRegular,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W500,
                                color = Color.Black
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.img_1),
                            contentDescription = "My Image",
                            modifier = Modifier.size(100.dp),
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun CircleIcon(iconId: Int) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(Color(0xFFFFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(20.dp)
        )
    }
}
@Composable
fun GreetingSection(name: String) {
    val WorkSansSemibold = FontFamily(
        Font(R.font.semibold)
    )
    val WorkSansRegular= FontFamily(
        Font(R.font.worksan_regular)
    )
    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier
                .padding(bottom = 5.dp)
        ) {
            Text(
                text = "Chào, ",
                style = TextStyle(
                    fontFamily = WorkSansRegular,
                    fontWeight = FontWeight.W400,
                    fontSize = 30.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF504D5D)
                )

            )
            Text(
                text = "$name!",
                style = TextStyle(
                    fontFamily = WorkSansSemibold,
                    fontWeight = FontWeight.W500,
                    fontSize = 30.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF48A05D)
                )

            )
        }

        Text(
            text = "Tiếp tục với hành trình của bạn nào.",
            style = TextStyle(
                fontFamily = WorkSansRegular,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.sp,
                color = Color(0xFF48A05D)
            )
        )
    }
}
@Composable
fun CityCardList() {
    Column(modifier = Modifier.padding(16.dp)) {
        CityCard("Bài test 1", "level 3", Color(0xFFE1BEE7), isLocked = false)
        Spacer(modifier = Modifier.height(12.dp))
        CityCard("Kota Olahraga", "level 1", Color(0xFFFFCDD2), isLocked = false)
        Spacer(modifier = Modifier.height(12.dp))
        CityCard("Kota Inggris", "Segera dimulai", Color(0xFFBBDEFB), isLocked = true)
    }
}
@Composable
fun CityCard(title: String, level: String, bgColor: Color, isLocked: Boolean) {
    val WorkSansSemibold = FontFamily(
        Font(R.font.semibold)
    )
    val WorkSansRegular= FontFamily(
        Font(R.font.worksan_regular)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(158.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(bgColor)

    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Box(
                modifier = Modifier
                    .padding(start =16.dp, top = 16.dp)
                    .size(44.dp)
                    .border(2.dp, Color(0xFFFFFFFF), RoundedCornerShape(6.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = if (isLocked) painterResource(id = R.drawable.ic_lock_slash)else painterResource(id = R.drawable.ic_play),
                    contentDescription = null,
                    tint = Color(0xFFFFFFFF),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier.padding(start =16.dp)
            ){
                Text(
                    text = level,
                    style = TextStyle(
                        fontFamily = WorkSansRegular,
                        fontWeight = FontWeight.W500,
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.sp,
                        color = Color(0xFFFFFFFF)
                    )
                )
                Text(
                    text = title,
                    style = TextStyle(
                        fontFamily = WorkSansSemibold,
                        fontWeight = FontWeight.W500,
                        fontSize = 22.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.sp,
                        color = Color(0xFFFFFFFF)
                    )

                )

            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_g12),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .height(90.dp)
        )
    }
}

@Composable
fun BottomNavigationBar() {
    Box {

        NavigationBar(
            containerColor = Color.White,
            tonalElevation = 8.dp,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.White)
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = null,
                        tint = Color(0xFF00BD50),
                        modifier = Modifier.size(24.dp)
                    )
                },
                selected = false,
                onClick = { /* xử lý click */ }
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shop),
                        contentDescription = null,
                        tint = Color(0xFF545454),
                        modifier = Modifier.size(24.dp)
                    )
                },
                selected = false,
                onClick = { }
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clipboard),
                        contentDescription = null,
                        tint = Color(0xFF545454),
                        modifier = Modifier.size(24.dp)
                    )
                },
                selected = false,
                onClick = { }
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_category_2),
                        contentDescription = null,
                        tint = Color(0xFF545454),
                        modifier = Modifier.size(24.dp)
                    )
                },
                selected = false,
                onClick = { }
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = null,
                        tint = Color(0xFF545454),
                        modifier = Modifier.size(24.dp)
                    )
                },
                selected = false,
                onClick = { }
            )
        }
    }
}


