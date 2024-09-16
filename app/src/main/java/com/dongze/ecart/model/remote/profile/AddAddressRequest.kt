package com.dongze.ecart.model.remote.profile

import com.google.gson.annotations.SerializedName

data class AddAddressRequest(
    val address: String,
    val title: String,
    @SerializedName("user_id")
    val userId: Int
)