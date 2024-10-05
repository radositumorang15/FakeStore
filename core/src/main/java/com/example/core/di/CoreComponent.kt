package com.example.core.di

import com.example.core.domain.repository.ProductRepository
import com.example.core.di.GetProductsUseCase
import dagger.Component
import javax.inject.Singleton

// CoreComponent.kt
@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {
    fun provideGetProductsUseCase(): GetProductsUseCase

    @Component.Factory
    interface Factory {
        fun create(): CoreComponent
    }
}
