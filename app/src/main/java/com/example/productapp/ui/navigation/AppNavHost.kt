package com.example.productapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.productapp.ui.details.ProductDetailsScreen
import com.example.productapp.ui.login.LoginScreen
import com.example.productapp.ui.products.ProductListScreen

object Routes {
    const val LOGIN = "login"
    const val PRODUCT_LIST = "product_list"
    const val PRODUCT_DETAILS = "product_details/{productId}"

    fun productDetails(productId: Int): String = "product_details/$productId"
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.PRODUCT_LIST) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.PRODUCT_LIST) {
            ProductListScreen(
                onProductClick = { productId ->
                    navController.navigate(Routes.productDetails(productId))
                }
            )
        }
        composable(
            route = Routes.PRODUCT_DETAILS,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId").orEmpty()
            ProductDetailsScreen(
                productId = productId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
