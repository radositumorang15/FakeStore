import androidx.compose.material3.* // Make sure to import Material3 components
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.Modifier
import com.example.productapp.presentation.ProductViewModel
import com.example.productapp.ui.ProductCard



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val products by viewModel.products.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product List") },
                actions = {
                    IconButton(onClick = { navController.navigate("favorite") }) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorites")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onFavoriteClick = {
                        viewModel.toggleFavorite(product.id)
                    },
                    onClick = {
                        // Navigasi ke DetailScreen dengan productId
                        navController.navigate("productDetail/${product.id}")
                    }
                )
            }
        }
    }
}
