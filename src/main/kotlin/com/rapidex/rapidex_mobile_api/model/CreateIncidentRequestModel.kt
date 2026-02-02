package com.rapidex.rapidex_mobile_api.model

data class CreateIncidentRequestModel(
    val orderId: Int,
    val incidentType: String,
    var incidentDescription: String
)
