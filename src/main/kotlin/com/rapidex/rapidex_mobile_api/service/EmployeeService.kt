package com.rapidex.rapidex_mobile_api.service

import com.rapidex.rapidex_mobile_api.dto.EmployeeDTO
import com.rapidex.rapidex_mobile_api.entities.Employee
import com.rapidex.rapidex_mobile_api.exceptions.BadRequestException
import com.rapidex.rapidex_mobile_api.exceptions.NotFoundException
import com.rapidex.rapidex_mobile_api.model.CreateEmployeeRequest
import com.rapidex.rapidex_mobile_api.model.LoginRequestModel
import com.rapidex.rapidex_mobile_api.repositories.EmployeeRepository
import org.springframework.stereotype.Service

@Service
class EmployeeService(private val employeeRepository: EmployeeRepository) {
    fun login(request: LoginRequestModel): EmployeeDTO {
        val employee = employeeRepository.findByUsername(request.username)
            ?: throw BadRequestException("Invalid username or password")

        if (employee.password != request.password) {
            throw BadRequestException("Invalid username or password")
        }

        return EmployeeDTO(
            id = employee.id,
            firstName = employee.firstName,
            lastName = employee.lastName,
            username = employee.username
        )
    }

    fun createEmployee(request: CreateEmployeeRequest) {

        if (employeeRepository.findByUsername(request.username) != null) {
            throw BadRequestException("This username already exists!")
        }

        val employee = Employee(
            firstName = request.firstName,
            lastName = request.lastName,
            username = request.username,
            password = request.password
        )

        employeeRepository.save(employee)
    }

    fun getAllEmployees(): List<Employee> = employeeRepository.findAll()

    fun deleteEmployee(employeeId: Int) {
        if (!employeeRepository.existsById(employeeId)) {
            throw BadRequestException("Employee not found")
        }

        employeeRepository.deleteById(employeeId)
    }

}