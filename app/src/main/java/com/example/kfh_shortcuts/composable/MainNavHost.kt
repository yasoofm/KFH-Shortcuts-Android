package com.example.kfh_shortcuts.composable

import HistoryScreen
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
import androidx.navigation.compose.composable

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
                modifier = Modifier.padding(bottom = 30.dp, end = 16.dp)
            ) {
                Icon(Icons.Default.Face, contentDescription = "Chat")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
                BottomNavBar(navController)

        }
    )
    {
        NavHost(
            navController = navController,
            startDestination = Routes.catalogRoute,
            modifier = Modifier
                .padding(it)
        ) {
            composable(Routes.chatbotRoute) {
                ChatBotScreen()
            }
            composable(Routes.catalogRoute) {
                CatalogScreen(
                    viewModel,
                    openProductDetails = { navController.navigate(Routes.DetailsRoute) })
            }
            composable(Routes.DetailsRoute) {
                DetailScreen(
                    viewModel,
                    openRequestDetails = { navController.navigate(Routes.RequestProductRoute) })
            }


            composable(Routes.RequestProductRoute) {
                SendRequest(
                    viewModel,
                    openSendRequest = { navController.navigate(Routes.SendRequestRoute) })
            }


            composable(Routes.RequestProductRoute) {
                SendRequest(viewModel, returnToCatalog = { navController.navigate(Routes.catalogRoute) })
            }
            composable(Routes.HistoryRoute) {
                HistoryScreen()
            }
            composable(Routes.RewardRoute) {
                RewardsScreen()
            }



        }
    }
}
