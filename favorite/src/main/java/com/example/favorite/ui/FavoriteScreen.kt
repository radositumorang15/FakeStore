package com.example.favorite.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.di.CoreComponent
import com.example.favorite.presentation.FavoriteViewModel
import com.example.favorite.presentation.FavoriteViewModelFactory
import com.example.favorite.di.FavoriteComponent

@Composable
fun FavoriteScreen(coreComponent: CoreComponent) {
    // Buat FavoriteComponent menggunakan CoreComponent
    val favoriteComponent: FavoriteComponent = DaggerFavoriteComponent.factory().create(coreComponent)

    // Injeksi ViewModel dengan FavoriteComponent
    val viewModel: FavoriteViewModel = viewModel {
        FavoriteViewModelFactory(favoriteComponent.provideGetProductsUseCase())
    }

    val favoriteProducts by viewModel.favoriteProducts.collectAsState()

    if (favoriteProducts.isEmpty()) {
        Text(
            text = "No favorite products available.",
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    } else {
        LazyColumn {
            items(favoriteProducts) { product ->
                FavoriteProductCard(
                    product = product,
                    onClick = { /* Navigate to product detail if needed */ },
                    onLikeClick = {
                        viewModel.toggleFavorite(product.id, !product.isFavorite)
                    }
                )
            }
        }
    }
}
