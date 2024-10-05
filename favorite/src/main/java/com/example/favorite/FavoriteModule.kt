package com.example.favorite.di

import com.example.core.data.api.ProductApiService
import com.example.core.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object FavoriteModule {

    // Provide ProductApiService dari modul core
    @Provides
    @Singleton
    fun provideProductApiService(
        apiService: ProductApiService
    ): ProductApiService = apiService

    // Provide AppDatabase dari modul core
    @Provides
    @Singleton
    fun provideAppDatabase(
        database: AppDatabase
    ): AppDatabase = database
}
