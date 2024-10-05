package com.example.productapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.core.domain.model.ProductDomainModel

@Composable
fun ProductCard(
    product: ProductDomainModel,
    onFavoriteClick: (Boolean) -> Unit,
    onClick: () -> Unit // Pastikan parameter ini ada
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }, // Pastikan ini diatur
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "${product.price}$", style = MaterialTheme.typography.bodyMedium)
            }

            IconButton(onClick = { onFavoriteClick(!product.isFavorite) }) {
                if (product.isFavorite) {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Liked")
                } else {
                    Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Like")
                }
            }
        }
    }
}
