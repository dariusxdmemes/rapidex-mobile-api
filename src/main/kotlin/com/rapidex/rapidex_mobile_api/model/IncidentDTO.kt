package com.rapidex.rapidex_mobile_api.model

data class IncidentDTO(
    val id: Long,
    val type: String,
    val description: String?,
    val orderId: Long,
    val employeeId: Long,
    val employeeName: String
)
