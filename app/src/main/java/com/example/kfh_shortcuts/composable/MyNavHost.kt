package com.example.kfh_shortcuts.composable

import ChatBotScreen
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
        navController.navigate(Routes.AppScreenRoute)
    }
    NavHost(
        navController = navController,
        startDestination = Routes.loginRoute,
        modifier = modifier
    ) {
        composable(Routes.loginRoute) {
            LoginScreen(
                viewModel,
                onSignInClicked = { navController.navigate(Routes.AppScreenRoute) })
        }
        composable(Routes.AppScreenRoute) {
            viewModel.fetchCategories()
            viewModel.fetchRewards()
            viewModel.requestHistory()
            viewModel.points()
            MainNavHost(viewModel = viewModel, navControllerOutter = navController)


            MainNavHost(
                viewModel = viewModel,
                navControllerOutter = navController
            )
        }
        composable(Routes.chatbotRoute) {
            ChatBotScreen(viewModel = viewModel)

        }
    }
}
