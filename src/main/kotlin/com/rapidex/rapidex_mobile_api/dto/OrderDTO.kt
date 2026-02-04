package com.rapidex.rapidex_mobile_api.dto

data class OrderDTO(
    val id: Int,
    val products: List<ProductDTO>,
    val employee: EmployeeDTO?,
    val prepDate: String?,
    val dispatchDate: String?
)
