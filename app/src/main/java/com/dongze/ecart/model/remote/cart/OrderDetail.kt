package com.dongze.ecart.model.remote.cart

import com.google.gson.annotations.SerializedName

data class OrderDetail(

    val address: String,
    @SerializedName("address_title")
    val addressTitle: String,
    @SerializedName("bill_amount")
    val billAmount: String,
    val items: List<ItemDetail>,
    @SerializedName("order_date")
    val orderDate: String,
    @SerializedName("order_id")
    val orderId: String,
    @SerializedName("order_status")
    val orderStatus: String,
    @SerializedName("payment_method")
    val paymentMethod: String
)