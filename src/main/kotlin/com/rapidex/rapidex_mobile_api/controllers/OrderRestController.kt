package com.rapidex.rapidex_mobile_api.controllers

import com.rapidex.rapidex_mobile_api.dto.OrderDTO
import com.rapidex.rapidex_mobile_api.entities.Order
import com.rapidex.rapidex_mobile_api.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/orders")
class OrderRestController(private val service: OrderService) {

    @GetMapping
    fun getAllOrders() = service.getAllOrders()

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: Int): OrderDTO = service.getOrderById(id)

    @GetMapping("/pending")
    fun getPendingOrders() = service.getPendingOrders()

    @GetMapping("/claimed/{employeeId}")
    fun getClaimedOrdersByEmployee(
        @PathVariable employeeId: Int
    ): List<Order> = service.getClaimedOrdersByEmployee(employeeId)

    @PostMapping("/claim/{orderId}/{employeeId}")
    fun claimOrder(
        @PathVariable orderId: Int,
        @PathVariable employeeId: Int
    ): OrderDTO = service.claimOrder(orderId, employeeId)

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOrder(
        @PathVariable orderId: Int
    ) {
        service.deleteOrder(orderId)
    }
}