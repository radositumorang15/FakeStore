package com.example.favorite.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.di.GetProductsUseCase
import com.example.core.domain.model.ProductDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

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
