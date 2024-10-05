package com.example.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core.data.local.entity.LocalProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    // Mengambil semua produk
    @Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<LocalProduct>>

    // Memasukkan produk ke database lokal
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<LocalProduct>)

    // Mengambil produk favorit
    @Query("SELECT * FROM product WHERE isFavorite = 1")
    fun getFavoriteProducts(): Flow<List<LocalProduct>>

    // Mendapatkan produk berdasarkan ID
    @Query("SELECT * FROM product WHERE id = :productId LIMIT 1")
    suspend fun getProductById(productId: Int): LocalProduct?

    // Mengubah status favorit produk
    @Query("UPDATE product SET isFavorite = :isFavorite WHERE id = :productId")
    suspend fun updateFavoriteStatus(productId: Int, isFavorite: Boolean)


}
