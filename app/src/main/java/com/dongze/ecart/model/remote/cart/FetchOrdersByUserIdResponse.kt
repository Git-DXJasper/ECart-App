package com.dongze.ecart.model.remote.cart

data class FetchOrdersByUserIdResponse(
    val message: String,
    val orders: List<Order>,
    val status: Int
)