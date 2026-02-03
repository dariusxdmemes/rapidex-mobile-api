package com.rapidex.rapidex_mobile_api.service

import com.rapidex.rapidex_mobile_api.entities.Order
import com.rapidex.rapidex_mobile_api.exceptions.NotFoundException
import com.rapidex.rapidex_mobile_api.repositories.EmployeeRepository
import com.rapidex.rapidex_mobile_api.repositories.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
        private val orderRepository: OrderRepository,
        private val employeeRepository: EmployeeRepository
) {

        fun getAllOrders(): List<Order> {
                val orders = orderRepository.getAllOrders()

                return orders
        }

        fun getPendingOrders(): List<Order> {
                val pendingOrders = orderRepository.getPendingOrders()

                return pendingOrders
        }

        fun getClaimedOrdersByEmployee(employeeId: Int): List<Order> {
                employeeRepository.findById(employeeId.toLong())
                        .orElseThrow { NotFoundException("The specified employee does not exist") }

                return orderRepository.getClaimedOrdersByEmployee(employeeId)
        }

}