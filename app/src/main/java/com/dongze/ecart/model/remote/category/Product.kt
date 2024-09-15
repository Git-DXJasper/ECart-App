package com.dongze.ecart.model.remote.category

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("average_rating")
    val averageRating: String,
    @SerializedName("category_id")
    val cid: String,
    @SerializedName("category_name")
    val cname: String,
    val description: String,
    val price: String,
    @SerializedName("product_id")
    val pid: String,
    @SerializedName("product_image_url")
    val pImgUrl: String,
    @SerializedName("product_name")
    val pname: String,
    @SerializedName("sub_category_id")
    val subCid: String,
    @SerializedName("subcategory_name")
    val subCname: String
)