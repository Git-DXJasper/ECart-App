package com.dongze.ecart.model.remote.dashboard

data class SearchResponse(
    val message: String,
    val products: List<ProductDetail>,
    val status: Int
)