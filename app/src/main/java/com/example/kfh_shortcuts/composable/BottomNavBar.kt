package com.example.kfh_shortcuts.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kfh_shortcuts.utiles.Routes


@Composable
fun BottomNavBar(navController: NavHostController) {
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
        NavItem(Icons.Default.Home, "Catalog") { navController.navigate(Routes.catalogRoute) }
        NavItem(Icons.Default.List, "History") { navController.navigate(Routes.HistoryRoute) }
        NavItem(Icons.Default.Star, "Rewards") { navController.navigate(Routes.RewardRoute) }
    }
}

@Composable
fun NavItem(icon: ImageVector, label: String, navigateToScreen: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
       navigateToScreen()

        }
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
