package com.rapidex.rapidex_mobile_api.model

data class UpdateProductRequestModel(
    val productName: String,
    val productCategory: String,
    val productDescription: String?,
    val productImageUrl: String?
)
