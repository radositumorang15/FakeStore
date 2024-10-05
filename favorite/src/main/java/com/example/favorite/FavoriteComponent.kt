package com.example.favorite.di

import com.example.core.di.CoreComponent
import com.example.core.di.GetProductsUseCase
import com.example.favorite.presentation.FavoriteViewModel
import dagger.Component
import javax.inject.Singleton

// FavoriteComponent.kt
@Singleton
@Component(
    dependencies = [CoreComponent::class], // Menggunakan CoreComponent sebagai dependensi
    modules = [FavoriteModule::class]
)
interface FavoriteComponent {
    fun provideGetProductsUseCase(): GetProductsUseCase

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): FavoriteComponent
    }
}
