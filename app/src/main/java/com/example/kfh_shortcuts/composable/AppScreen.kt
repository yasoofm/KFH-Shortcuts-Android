package com.example.kfh_shortcuts.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.geometry.Offset

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppScreen() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Add action */ },
                shape = CircleShape,
                containerColor = Color(0xFF007A3D),
                contentColor = Color.White,
                modifier = Modifier.padding(bottom = 16.dp, end = 16.dp)
            ) {
                Icon(Icons.Default.Face, contentDescription = "chat")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                BottomNavBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                )
            }
        },
        topBar = {
            TopBar()
        }
    ) {

    }
}

@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(bottomStart = 29.dp, bottomEnd = 29.dp)
            }
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF007A3D), Color(0xFF0D4228), Color(0xFF000000)),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
    )
}

@Composable
fun BottomNavBar(modifier: Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0D4228),
                        Color(0xFF007A3D),
                        Color.Black
                    )
                )
            )
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavItem(Icons.Default.Home, "Home")
        NavItem(Icons.Default.List, "History")
        NavItem(Icons.Default.Star, "Rewards")
    }
}

@Composable
fun NavItem(icon: ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { /* TODO: Add action */ }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}



@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home Screen")
    }
}

@Composable
fun HistoryScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "History Screen")
    }
}

@Composable
fun RewardsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Rewards Screen")
    }
}

@Composable
fun ChatBotScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "chat Screen")
    }
}




//onlie design first one
//package com.example.kfh_shortcuts.composable
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.List
//import androidx.compose.material.icons.filled.Star
//import androidx.compose.material.icons.filled.Face
//import androidx.compose.ui.geometry.Offset
//
//@Composable
//fun AppScreen() {
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { /* TODO: Add action */ },
//                shape = CircleShape,
//                containerColor = Color(0xFF007A3D),
//                contentColor = Color.White,
//                modifier = Modifier
//                    .padding(bottom = 16.dp, end = 16.dp)
//            ) {
//                Icon(Icons.Default.Face, contentDescription = "Face")
//            }
//        },
//        floatingActionButtonPosition = FabPosition.End,
//        bottomBar = {
//            BottomAppBar(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(95.dp)
//            ) {
//                BottomNavBar(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(80.dp)
//                )
//            }
//        },
//        topBar = {
//            TopBar()
//        }
//    ) {
//        Spacer(modifier = Modifier.padding(it))
//    }
//}
//
//@Composable
//fun TopBar() {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(230.dp)
//            .graphicsLayer {
//                clip = true
//                shape = RoundedCornerShape(bottomStart = 29.dp, bottomEnd = 29.dp)
//            }
//            .background(
//                brush = Brush.linearGradient(
//                    colors = listOf(Color(0xFF007A3D), Color(0xFF0D4228), Color(0xFF000000)),
//                    start = Offset.Zero,
//                    end = Offset.Infinite
//                )
//            )
//    )
//}
//
//@Composable
//fun BottomNavBar(modifier: Modifier) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(
//                Brush.linearGradient(
//                    colors = listOf(
//                        Color(0xFF0D4228),
//                        Color(0xFF007A3D),
//                        Color.Black
//                    )
//                )
//            )
//            .padding(vertical = 10.dp),
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        NavItem(Icons.Default.Home, "Home")
//        NavItem(Icons.Default.List, "History")
//        NavItem(Icons.Default.Star, "Rewards")
//    }
//}
//
//@Composable
//fun NavItem(icon: ImageVector, label: String) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Icon(
//            imageVector = icon,
//            contentDescription = label,
//            tint = Color.White,
//            modifier = Modifier.size(30.dp)
//        )
//        Text(
//            text = label,
//            color = Color.White,
//            fontSize = 18.sp
//        )
//    }
//}
