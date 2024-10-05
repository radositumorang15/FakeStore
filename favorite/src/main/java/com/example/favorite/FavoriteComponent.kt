package com.example.favorite.di

import com.example.core.di.CoreComponent
import com.example.favorite.FavoriteModule
import com.example.favorite.presentation.FavoriteViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [CoreComponent::class], // Menggunakan dependensi dari `core`
    modules = [FavoriteModule::class] // Modul untuk menyediakan dependensi
)
interface FavoriteComponent {
    fun inject(viewModel: FavoriteViewModel)

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): FavoriteComponent
    }
}
