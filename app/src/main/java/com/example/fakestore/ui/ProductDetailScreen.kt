package com.example.fakestore.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.core.domain.model.ProductDomainModel

@Composable
fun ProductDetailScreen(product: ProductDomainModel?) {
    // Use a Box to allow alignment of the CircularProgressIndicator
    Box(modifier = Modifier.fillMaxSize()) {
        if (product == null) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(64.dp) // Adjust size as needed
                    .align(Alignment.Center) // Center the indicator
            )
        } else {
            // Only display product details when the product is available
            Column(modifier = Modifier.padding(16.dp)) {
                // Display product image
                Image(
                    painter = rememberImagePainter(data = product.imageUrl),
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Display product name
                Text(text = product.name, style = MaterialTheme.typography.headlineSmall)

                // Display product price
                Text(text = "Price: \$${product.price}", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(8.dp))

                // Display product description
                Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductDetailScreen() {
    val sampleProduct = ProductDomainModel(
        id = 1,
        name = "Sample Product",
        price = 29.99,
        description = "This is a sample description for the product.",
        imageUrl = "https://via.placeholder.com/150"
    )
    ProductDetailScreen(product = sampleProduct)
}
