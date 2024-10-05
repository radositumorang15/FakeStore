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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.favorite.presentation.FavoriteViewModel

@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel = hiltViewModel()) {
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