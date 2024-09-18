package com.dongze.ecart.model.remote.cart

data class GetOrderDetailResponse(
    val message: String,
    val order: OrderDetail,
    val status: Int
)