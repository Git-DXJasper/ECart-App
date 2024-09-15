package com.dongze.ecart.model.remote.category

data class GetCategoryResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)