package com.dongze.ecart.model.remote.dashboard

import com.google.gson.annotations.SerializedName

data class ProductDetail(
    @SerializedName("average_rating")
    val averageRating: String,
    @SerializedName("category_id")
    val cid: String,
    val description: String,
    val images: List<Image>,
    @SerializedName("is_active")
    val isActive: String,
    val price: String,
    @SerializedName("product_id")
    val pid: String,
    @SerializedName("product_image_url")
    val pImgUrl: String,
    @SerializedName("product_name")
    val pname: String,
    val reviews: List<Review>,
    val specifications: List<Specification>,
    @SerializedName("sub_category_id")
    val subCid: String
)