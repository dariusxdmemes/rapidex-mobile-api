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
        /* Returns a list of Order
        * the orders marked as "pending"
        * are orders that:
        * 1- dont have an employee_id
        * 2- dont have prep_date
        * 3- dont have dispatch_date */
        fun getPendingOrders(): List<Order> = orderRepository.getPendingOrders()

}