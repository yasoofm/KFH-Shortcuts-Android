package com.example.kfh_shortcuts.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kfh_shortcuts.utiles.Routes
import com.example.kfh_shortcuts.viewmodel.ProductViewModel
import androidx.navigation.compose.NavHost

@Composable
fun MyNavHost(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    if (viewModel.token?.token != null) {
        navController.navigate(Routes.catalogRoute)
    }

    NavHost(
        navController = navController,
        startDestination = Routes.loginRoute,
        modifier = modifier
    ) {

        composable(Routes.loginRoute) {
            LoginScreen(
                viewModel,
                onSignInClicked = { navController.navigate(Routes.catalogRoute) })
        }


        composable(Routes.catalogRoute) {
            CatalogScreen(
                viewModel,
                OpenProductDetails = { navController.navigate(Routes.chatbotRoute) })

        }
//        composable(Routes.chatbotRoute) {
//            CatalogScreen(
//                viewModel,
//                //openChatBotClicked = { navController.navigate(Routes.chatbotRoute) })
//        }
    }
}
