package com.example.kfh_shortcuts.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kfh_shortcuts.utiles.Routes
import com.example.kfh_shortcuts.viewmodel.ProductViewModel
import androidx.navigation.compose.NavHost

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.chatbotRoute)
                },
                shape = CircleShape,
                containerColor = Color(0xFF007A3D),
                contentColor = Color.White,
                modifier = Modifier.padding(bottom = 80.dp, end = 16.dp)
            ) {
                Icon(Icons.Default.Face, contentDescription = "Chat")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                BottomNavBar()
            }
        }
    )
    {
        NavHost(
            navController = navController,
            startDestination = Routes.loginRoute,
            modifier = Modifier
                .padding(it)
        ) {
        }
    }
}
