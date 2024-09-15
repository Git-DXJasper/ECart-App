package com.dongze.ecart.model.remote.dashboard

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("display_order")
    val displayOrder: String,
    val image: String
)