package com.example.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.GetProductsUseCase
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(getProductsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
