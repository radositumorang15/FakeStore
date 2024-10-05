package com.example.core.domain.repository

import android.util.Log
import com.example.core.data.api.ProductApiService
import com.example.core.data.local.dao.ProductDao
import com.example.core.data.mappers.toLocalProduct
import com.example.core.data.local.entity.LocalProduct
import com.example.core.data.mappers.toDomainModel
import com.example.core.domain.model.ProductDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ProductApiService,
    private val productDao: ProductDao
) {
    private val TAG = "ProductRepository"

    // Mengambil semua produk dari API dan database lokal
    fun getProducts(): Flow<List<LocalProduct>> = flow {
        Log.d(TAG, "Fetching products from API...")

        // Ambil produk dari API
        val apiProducts = apiService.getProducts()
        Log.d(TAG, "Fetched ${apiProducts.size} products from API.")

        val localProducts = mutableListOf<LocalProduct>()
        for (apiProduct in apiProducts) {
            val localProduct = productDao.getProductById(apiProduct.id) // Call suspend function correctly
            localProducts.add(apiProduct.toLocalProduct().copy(isFavorite = localProduct?.isFavorite ?: false))
        }

        // Simpan produk dari API ke dalam database lokal
        Log.d(TAG, "Inserting ${localProducts.size} products into the local database.")
        productDao.insertProducts(localProducts)
        Log.d(TAG, "Inserted products into local database: $localProducts")

        // Emit semua produk dari database lokal sebagai Flow
        Log.d(TAG, "Emitting all products from local database...")
        emitAll(productDao.getAllProducts())
    }

    // Get favorite products from the local database
    fun getFavoriteProducts(): Flow<List<LocalProduct>> {
        Log.d(TAG, "Fetching favorite products from local database...")
        return productDao.getFavoriteProducts()
            .map { favoriteProducts ->
                Log.d(TAG, "Fetched ${favoriteProducts.size} favorite products: $favoriteProducts")
                favoriteProducts
            }
    }
    // Update favorite status in the local database
    suspend fun updateFavoriteStatus(productId: Int, isFavorite: Boolean) {
        productDao.updateFavoriteStatus(productId, isFavorite)
    }

    // Mengubah status favorit produk
    suspend fun toggleFavorite(productId: Int, isFavorite: Boolean) {
        Log.d(TAG, "Toggling favorite status for product ID: $productId to $isFavorite")
        productDao.updateFavoriteStatus(productId, isFavorite)
        Log.d(TAG, "Updated favorite status for product ID: $productId in the database")
    }
}
