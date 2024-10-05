package com.example.core.data.mappers

import com.example.core.data.api.ApiProduct
import com.example.core.data.local.entity.LocalProduct
import com.example.core.domain.model.ProductDomainModel

fun ApiProduct.toLocalProduct(): LocalProduct {
    return LocalProduct(
        id = this.id ?: 0,
        name = this.name ?: "N/A",
        price = this.price ?: 0.0,
        description = this.description ?: "No description",
        imageUrl = this.imageUrl ?: ""
    )
}

fun LocalProduct.toDomainModel(): ProductDomainModel {
    return ProductDomainModel(
        id = this.id ?: 0,
        name = this.name ?: "N/A",
        price = this.price ?: 0.0,
        description = this.description ?: "No description",
        imageUrl = this.imageUrl ?: ""
    )


}