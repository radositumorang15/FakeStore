package com.example.core.data.api

import com.google.gson.annotations.SerializedName

data class ApiProduct(
    val id: Int,
    @field:SerializedName("title")
    val name: String,
    val price: Double,
    val description:String,
    @field:SerializedName("image")
    val imageUrl: String
)
