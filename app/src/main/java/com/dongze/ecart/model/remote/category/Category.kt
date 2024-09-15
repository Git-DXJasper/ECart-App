package com.dongze.ecart.model.remote.category

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("category_id")
    val cid: String,
    @SerializedName("category_image_url")
    val imgUrl: String,
    @SerializedName("category_name")
    val cname: String,
    @SerializedName("is_active")
    val isActive: String
)