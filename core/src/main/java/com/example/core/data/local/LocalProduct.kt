package com.example.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class LocalProduct(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String,
    var isFavorite: Boolean = false // Menyimpan status favorit produk
)
