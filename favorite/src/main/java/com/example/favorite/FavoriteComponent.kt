package com.example.favorite

import com.example.core.di.CoreComponent
import com.example.favorite.presentation.FavoriteViewModel
import com.example.favorite.presentation.FavoriteViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [CoreComponent::class],
    modules = [FavoriteModule::class]
)
interface FavoriteComponent {
    fun inject(viewModel: FavoriteViewModel)

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): FavoriteComponent
    }
}
