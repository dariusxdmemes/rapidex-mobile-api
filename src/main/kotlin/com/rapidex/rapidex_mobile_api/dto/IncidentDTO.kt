package com.rapidex.rapidex_mobile_api.dto

data class IncidentDTO(
    val id: Int,
    val type: String,
    val description: String?,
    val orderId: Int,
    val employeeId: Int,
    val employeeName: String
)