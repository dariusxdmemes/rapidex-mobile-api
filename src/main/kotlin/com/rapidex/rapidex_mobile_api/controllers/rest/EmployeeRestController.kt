package com.rapidex.rapidex_mobile_api.controllers.rest

import com.rapidex.rapidex_mobile_api.dto.EmployeeDTO
import com.rapidex.rapidex_mobile_api.model.LoginRequestModel
import com.rapidex.rapidex_mobile_api.service.EmployeeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/employees")
class EmployeeRestController(
    private val service: EmployeeService
) {

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequestModel
    ): EmployeeDTO {
        return service.login(request)
    }
}