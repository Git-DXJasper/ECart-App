package com.dongze.ecart.model.remote.dashboard

data class GetDetailByPIdResponse(
    val message: String,
    val product: ProductDetail,
    val status: Int
)
