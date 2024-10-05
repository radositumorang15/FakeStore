package com.example.core.di

import android.app.Application
import androidx.room.Room
import com.example.core.data.api.ProductApiService
import com.example.core.data.local.AppDatabase
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
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app_database" // Ganti dengan nama database Anda
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductApiService(): ProductApiService {
        return ProductApiService.create()
    }

    @Provides
    @Singleton
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

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
