package com.rapidex.rapidex_mobile_api.model

data class ApiError(
    val status: Int,
    val message: String?,
    val timestamp: Long = System.currentTimeMillis()
)
