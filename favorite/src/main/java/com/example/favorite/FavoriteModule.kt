package com.example.favorite.di

import com.example.core.di.GetProductsUseCase
import com.example.favorite.presentation.FavoriteViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object FavoriteModule {

    @Provides
    @Singleton
    fun provideFavoriteViewModelFactory(
        getProductsUseCase: GetProductsUseCase
    ): FavoriteViewModelFactory {
        return FavoriteViewModelFactory(getProductsUseCase)
    }
}
