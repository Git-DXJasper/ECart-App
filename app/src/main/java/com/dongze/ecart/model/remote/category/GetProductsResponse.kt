package com.dongze.ecart.model.remote.category

data class GetProductsResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)