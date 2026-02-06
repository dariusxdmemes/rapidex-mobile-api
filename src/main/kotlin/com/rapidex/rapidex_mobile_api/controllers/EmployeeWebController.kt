package com.rapidex.rapidex_mobile_api.controllers

import com.rapidex.rapidex_mobile_api.model.CreateEmployeeRequest
import com.rapidex.rapidex_mobile_api.service.EmployeeService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/rapidex/employees")
class EmployeeWebController(private val employeeService: EmployeeService) {

    @GetMapping
    fun listEmployees(model: Model): String {
        model.addAttribute("employees", employeeService.getAllEmployees())

        return "employees/list"
    }

    @GetMapping("/new")
    fun newEmployeeForm(model: Model): String {
        model.addAttribute("employee", CreateEmployeeRequest())

        return "employees/new"
    }

    @PostMapping
    fun createEmployee(request: CreateEmployeeRequest): String {
        employeeService.createEmployee(request)

        return "redirect:/rapidex/employees"
    }

    @PostMapping("/{id}/delete")
    fun deleteEmployee(@PathVariable id: Int): String {
        employeeService.deleteEmployee(id)

        return "redirect:/rapidex/employees"
    }
}