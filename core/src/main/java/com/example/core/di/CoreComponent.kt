package com.example.core.di

import android.app.Application
import android.content.Context
import com.example.core.data.api.ProductApiService
import com.example.core.data.local.dao.ProductDao
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {

    fun getProductsUseCase(): GetProductsUseCase

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance productApiService: ProductApiService,
            @BindsInstance productDao: ProductDao
        ): CoreComponent
    }
}
