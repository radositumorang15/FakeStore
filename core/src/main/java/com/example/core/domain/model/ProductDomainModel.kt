package com.example.core.domain.model

data class ProductDomainModel(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String,
    var isFavorite: Boolean = false // Menyimpan status favorit untuk ditampilkan di UI
)
