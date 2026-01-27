package com.rapidex.rapidex_mobile_api.service

import com.rapidex.rapidex_mobile_api.entities.Order
import com.rapidex.rapidex_mobile_api.repositories.EmployeeRepository
import com.rapidex.rapidex_mobile_api.repositories.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
        private val orderRepository: OrderRepository,
        private val employeeRepository: EmployeeRepository
) {
        fun getAll(): List<Order> = orderRepository.findAll()

        fun getAllById(id: Long): Order = orderRepository.findById(id)
                .orElseThrow { RuntimeException("El pedido con ID $id no existe") }

        fun createOrder(order: Order): Order = orderRepository.save(order)

        fun deleteOrder(id: Long) {
                if (!orderRepository.existsById(id)) throw RuntimeException("El pedido con ID $id no existe")
                orderRepository.deleteById(id)
        }

        fun unassignOrder(orderId: Long): Order {
                val order = orderRepository.findById(orderId)
                        .orElseThrow { RuntimeException("Pedido con ID $orderId no existe") }

                order.employee = null

                return orderRepository.save(order)
        }
}