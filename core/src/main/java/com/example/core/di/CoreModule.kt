package com.example.core.di

import com.example.core.data.api.ProductApiService
import com.example.core.data.local.dao.ProductDao
import com.example.core.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideProductRepository(
        apiService: ProductApiService,
        productDao: ProductDao
    ): ProductRepository {
        return ProductRepository(apiService, productDao)
    }

    @Provides
    fun provideGetProductsUseCase(repository: ProductRepository): GetProductsUseCase {
        return GetProductsUseCase(repository)
    }
}
