package com.rapidex.rapidex_mobile_api.controllers.web

import com.rapidex.rapidex_mobile_api.model.CreateEmployeeRequest
import com.rapidex.rapidex_mobile_api.model.UpdateEmployeeRequest
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

    @GetMapping("/{id}")
    fun employeeDetail(@PathVariable id: Int, model: Model): String {
        val employee = employeeService.getEmployeeById(id)
        model.addAttribute("employee", employee)

        return "employees/detail"
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

    @GetMapping("/{id}/edit")
    fun editEmployeeForm(@PathVariable id: Int, model: Model): String {
        val employee = employeeService.getEmployeeById(id)

        model.addAttribute("employee", employee)
        model.addAttribute(
            "updateRequest",
            UpdateEmployeeRequest(
                firstName = employee.firstName,
                lastName = employee.lastName,
                password = employee.password
            )
        )

        return "employees/edit"
    }

    @PostMapping("/{id}/edit")
    fun updateEmployee(@PathVariable id: Int, request: UpdateEmployeeRequest): String {
        employeeService.updateEmployee(id, request)

        return "redirect:/rapidex/employees/$id"
    }
}