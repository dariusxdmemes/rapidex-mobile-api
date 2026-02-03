package com.rapidex.rapidex_mobile_api.controllers

import com.rapidex.rapidex_mobile_api.entities.Employee
import com.rapidex.rapidex_mobile_api.entities.Order
import com.rapidex.rapidex_mobile_api.service.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/orders")
class OrderRestController(private val service: OrderService) {

    @GetMapping
    fun getAllOrders() = service.getAllOrders()

    @GetMapping("/pending")
    fun getPendingOrders() = service.getPendingOrders()

    @GetMapping("/claimed/{employeeId}")
    fun getClaimedOrdersByEmployee(
        @PathVariable employeeId: Int
    ): List<Order> = service.getClaimedOrdersByEmployee(employeeId)
}