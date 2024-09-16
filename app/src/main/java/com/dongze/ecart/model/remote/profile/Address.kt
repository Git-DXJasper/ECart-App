package com.dongze.ecart.model.remote.profile

import com.google.gson.annotations.SerializedName

data class Address(
    val address: String,
    @SerializedName("address_id")
    val addrId: String,
    val title: String
)