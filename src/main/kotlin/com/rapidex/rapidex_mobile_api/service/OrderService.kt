package com.rapidex.rapidex_mobile_api.service

import com.rapidex.rapidex_mobile_api.dto.EmployeeDTO
import com.rapidex.rapidex_mobile_api.dto.OrderDTO
import com.rapidex.rapidex_mobile_api.dto.ProductDTO
import com.rapidex.rapidex_mobile_api.entities.Order
import com.rapidex.rapidex_mobile_api.exceptions.BadRequestException
import com.rapidex.rapidex_mobile_api.exceptions.NotFoundException
import com.rapidex.rapidex_mobile_api.repositories.EmployeeRepository
import com.rapidex.rapidex_mobile_api.repositories.OrderRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

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
                employeeRepository.findById(employeeId)
                        .orElseThrow { NotFoundException("The specified employee does not exist") }

                return orderRepository.getClaimedOrdersByEmployee(employeeId)
        }

        fun claimOrder(orderId: Int, employeeId: Int): OrderDTO {
                val order = orderRepository.findById(orderId)
                        .orElseThrow { NotFoundException("This order does not exist!") }

                if (order.employee != null) {
                        throw BadRequestException("This order is already claimed!")
                }

                val employee = employeeRepository.findById(employeeId)
                        .orElseThrow { NotFoundException("Employee not found!") }

                order.employee = employee
                order.prepDate = LocalDateTime.now()

                val savedOrder = orderRepository.save(order)

                return mapToDTO(savedOrder)
        }

        private fun mapToDTO(order: Order): OrderDTO =
                OrderDTO(
                        id = order.id,
                        products = order.products.map { product ->
                                ProductDTO(
                                        id = product.id,
                                        name = product.productName,
                                        category = product.productCategory,
                                        description = product.productDescription
                                )
                        },
                        employee = order.employee?.let { employee ->
                                EmployeeDTO(
                                        id = employee.id,
                                        firstName = employee.firstName,
                                        lastName = employee.lastName,
                                        username = employee.username
                                )
                        },
                        prepDate = order.prepDate?.toString(),
                        dispatchDate = order.dispatchDate?.toString()
                )

        fun deleteOrder(orderId: Int) {
                val order = orderRepository.findById(orderId)
                        .orElseThrow { NotFoundException("This order does not exist!") }

                if (order.employee == null) {
                        throw BadRequestException("This order is not assigned to any employee")
                }

                if (order.prepDate == null) {
                        throw BadRequestException("This order has no preparation date")
                }

                if (order.dispatchDate == null) {
                        throw BadRequestException("This order has no dispatch date")
                }

                orderRepository.delete(order)
        }
}