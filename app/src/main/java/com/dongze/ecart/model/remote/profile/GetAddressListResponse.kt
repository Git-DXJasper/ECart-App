package com.dongze.ecart.model.remote.profile

data class GetAddressListResponse(
    val addresses: List<Address>,
    val message: String,
    val status: Int
)