package com.rapidex.rapidex_mobile_api.controllers

import com.rapidex.rapidex_mobile_api.model.CreateIncidentRequestModel
import com.rapidex.rapidex_mobile_api.dto.IncidentDTO
import com.rapidex.rapidex_mobile_api.service.IncidentService
import org.apache.coyote.Request
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/incidents")
class IncidentRestController(private val service: IncidentService) {

    @GetMapping
    fun getAllIncidents() = service.getAllIncidents()

    @GetMapping("/employee/{employeeId}")
    fun getIncidentsByEmployee(
        @PathVariable employeeId: Long
    ): List<IncidentDTO> {
        return service.getIncidentsByEmployee(employeeId)
    }

    @PostMapping
    fun createIncident(
        @RequestBody request: CreateIncidentRequestModel
    ): IncidentDTO {
        return service.createIncident(request)
    }
}