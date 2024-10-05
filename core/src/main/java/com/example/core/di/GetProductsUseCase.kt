package com.example.core.di

import com.example.core.data.mappers.toDomainModel
import com.example.core.domain.model.ProductDomainModel
import com.example.core.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    // Get all products
    operator fun invoke(): Flow<List<ProductDomainModel>> {
        return repository.getProducts().map { localProducts ->
            localProducts.map { it.toDomainModel() }
        }
    }

    // Get favorite products
    fun getFavoriteProducts(): Flow<List<ProductDomainModel>> {
        return repository.getFavoriteProducts().map { localProducts ->
            localProducts.map { it.toDomainModel() }
        }
    }

    // Toggle favorite status for a product
    suspend fun toggleFavorite(productId: Int, isFavorite: Boolean) {
        repository.updateFavoriteStatus(productId, isFavorite)
    }
}
