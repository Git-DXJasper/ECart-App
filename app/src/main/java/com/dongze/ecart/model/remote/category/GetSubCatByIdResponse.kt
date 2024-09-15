package com.dongze.ecart.model.remote.category

data class GetSubCatByIdResponse(
    val message: String,
    val status: Int,
    val subcategories: List<Subcategory>
)