package com.dongze.ecart.model.remote.category

import com.google.gson.annotations.SerializedName

data class Subcategory(
    @SerializedName("category_id")
    val cid: String,
    @SerializedName("is_active")
    val isActive: String,
    @SerializedName("subcategory_id")
    val subCid: String,
    @SerializedName("subcategory_image_url")
    val subCatImgUrl: String,
    @SerializedName("subcategory_name")
    val subCname: String
)