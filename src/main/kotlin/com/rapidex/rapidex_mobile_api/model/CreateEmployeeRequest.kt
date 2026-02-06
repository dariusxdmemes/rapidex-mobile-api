package com.rapidex.rapidex_mobile_api.model

data class CreateEmployeeRequest(
    val firstName: String = "",
    val lastName: String = "",
    val username: String = "",
    val password: String = ""
)
