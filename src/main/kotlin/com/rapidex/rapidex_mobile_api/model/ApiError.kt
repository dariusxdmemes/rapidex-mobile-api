package com.rapidex.rapidex_mobile_api.model

data class ApiError(
    val status: Int,
    val error: String,
    val message: String?
)
