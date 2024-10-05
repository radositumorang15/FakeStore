package com.example.productapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.di.GetProductsUseCase
import com.example.core.domain.model.ProductDomainModel
import com.example.core.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductDomainModel>>(emptyList())
    val products: StateFlow<List<ProductDomainModel>> = _products.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                getProductsUseCase().collect { productList ->
                    _products.value = productList
                }
            } catch (e: CancellationException) {
                Log.e("ProductViewModel", "Job was cancelled", e)
            } catch (e: Exception) {
                _errorMessage.value = e.message
                Log.e("ProductViewModel", "Error fetching products", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getProductById(productId: Int): ProductDomainModel? {
        return _products.value.find { it.id == productId }
    }

    // Toggle Favorite Status
    fun toggleFavorite(productId: Int) {
        viewModelScope.launch {
            val product = _products.value.find { it.id == productId }
            product?.let {
                val updatedProduct = it.copy(isFavorite = !it.isFavorite)

                // Update the in-memory product list
                _products.value = _products.value.map { p ->
                    if (p.id == productId) updatedProduct else p
                }

                // Save the updated favorite status to the database
                productRepository.toggleFavorite(productId, updatedProduct.isFavorite)
            }
        }
    }
}

