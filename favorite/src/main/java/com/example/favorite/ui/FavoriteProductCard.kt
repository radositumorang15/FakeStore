package com.example.favorite.ui

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.core.domain.model.ProductDomainModel

@Composable
fun FavoriteProductCard(
    product: ProductDomainModel,
    onClick: () -> Unit = {},
    onLikeClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }, // Enable clickable functionality
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val imageUrl = product.imageUrl.takeIf { it.isNotBlank() } ?: "default_image_url"

            Image(
                painter = rememberImagePainter(data = imageUrl), // Load image from URL using Coil
                contentDescription = product.name,
                modifier = Modifier
                    .size(64.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start
                )
                product.price?.let {
                    Text(
                        text = "$${it}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            IconButton(onClick = onLikeClick) {
                if (product.isFavorite) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Liked",
                        tint = MaterialTheme.colorScheme.primary
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Like",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
