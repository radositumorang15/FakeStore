package com.example.fakestore.ui

import ProductListScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.favorite.ui.FavoriteScreen
import com.example.productapp.presentation.ProductViewModel

@Composable
fun ProductAppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "productList") {
        composable("productList") {
            ProductListScreen(navController = navController)
        }
        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
            val viewModel: ProductViewModel = hiltViewModel()
            val product = viewModel.getProductById(productId ?: 0)
            ProductDetailScreen(product = product)
        }
        composable("favorite") { // Add this line for the favorite screen
            FavoriteScreen()
  }
    }
}
