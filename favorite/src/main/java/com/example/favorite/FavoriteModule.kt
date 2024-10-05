package com.example.favorite

import com.example.core.di.GetProductsUseCase
import com.example.favorite.presentation.FavoriteViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteModule {

    @Provides
    @Singleton
    fun provideFavoriteViewModelFactory(
        getProductsUseCase: GetProductsUseCase
    ): FavoriteViewModelFactory {
        return FavoriteViewModelFactory(getProductsUseCase)
    }
}
