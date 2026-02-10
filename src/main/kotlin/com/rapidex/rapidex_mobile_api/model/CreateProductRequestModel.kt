package com.rapidex.rapidex_mobile_api.model

data class CreateProductRequestModel(
    val productName: String = "",
    val productCategory: String = "",
    val productDescription: String = "",
    val productImageUrl: String? = null
)
