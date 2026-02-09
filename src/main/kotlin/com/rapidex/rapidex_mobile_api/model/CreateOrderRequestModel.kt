package com.rapidex.rapidex_mobile_api.model

data class CreateOrderRequestModel(
    val productIds: List<Int> = emptyList()
)
