package com.rapidex.rapidex_mobile_api.service

import com.rapidex.rapidex_mobile_api.entities.Employee
import com.rapidex.rapidex_mobile_api.entities.Incident
import com.rapidex.rapidex_mobile_api.exceptions.BadRequestException
import com.rapidex.rapidex_mobile_api.exceptions.NotFoundException
import com.rapidex.rapidex_mobile_api.model.CreateIncidentRequestModel
import com.rapidex.rapidex_mobile_api.model.IncidentDTO
import com.rapidex.rapidex_mobile_api.repositories.IncidentRepository
import com.rapidex.rapidex_mobile_api.repositories.OrderRepository
import org.springframework.stereotype.Service

@Service
class IncidentService(
    private val incidentRepository: IncidentRepository,
    private val orderRepository: OrderRepository
) {
    fun getAllIncidents(): List<IncidentDTO> {
        return incidentRepository.findAll().map { incident ->
            IncidentDTO(
                id = incident.id,
                type = incident.type,
                description = incident.descrip,
                orderId = incident.order.id,
                employeeId = incident.employee.id,
                employeeName = "${incident.employee.firstName} ${incident.employee.lastName}"
            )
        }
    }

    fun getIncidentsByEmployee(employeeId: Long): List<IncidentDTO> {
        return incidentRepository.findByEmployeeId(employeeId).map { incident ->
            IncidentDTO(
                id = incident.id,
                type = incident.type,
                description = incident.descrip,
                orderId = incident.order.id,
                employeeId = incident.employee.id,
                employeeName = "${incident.employee.firstName} ${incident.employee.lastName}"
            )
        }
    }

    fun createIncident(request: CreateIncidentRequestModel): IncidentDTO {
        val order = orderRepository.findById(request.orderId)
            .orElseThrow { NotFoundException("Order not found") }

        val employee = order.employee
            ?: throw BadRequestException("Order not assigned to any employee")

        val incident = Incident(
            order = order,
            employee = employee,
            type = request.incidentType,
            descrip = request.incidentDescription
        )

        val saveIncident = incidentRepository.save(incident)

        return IncidentDTO(
            id = saveIncident.id,
            type = saveIncident.type,
            description = saveIncident.descrip,
            orderId = saveIncident.order.id,
            employeeId = saveIncident.employee.id,
            employeeName = "${saveIncident.employee.firstName} ${saveIncident.employee.lastName}"
        )
    }
}