package com.example.favorite.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.di.GetProductsUseCase
import com.example.core.domain.model.ProductDomainModel
import com.example.core.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _favoriteProducts = MutableStateFlow<List<ProductDomainModel>>(emptyList())
    val favoriteProducts = _favoriteProducts.asStateFlow()

    init {
        loadFavoriteProducts()
    }

    private fun loadFavoriteProducts() {
        viewModelScope.launch {
            getProductsUseCase.getFavoriteProducts().collect { products ->
                Log.d("FavoriteViewModel", "Favorite products: $products")
                _favoriteProducts.value = products
            }
        }
    }

    fun toggleFavorite(productId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            getProductsUseCase.toggleFavorite(productId, isFavorite)
            loadFavoriteProducts() // Refresh favorite products after the update
        }
    }
}
