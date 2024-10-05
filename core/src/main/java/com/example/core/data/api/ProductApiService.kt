package com.example.core.data.api

import retrofit2.http.GET

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): List<ApiProduct>
}
