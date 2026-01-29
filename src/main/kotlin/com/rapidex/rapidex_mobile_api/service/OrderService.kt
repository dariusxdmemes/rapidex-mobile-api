package com.rapidex.rapidex_mobile_api.service

import com.rapidex.rapidex_mobile_api.entities.Order
import com.rapidex.rapidex_mobile_api.exceptions.OrderNotFoundException
import com.rapidex.rapidex_mobile_api.exceptions.PendingOrderNotFoundException
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
        *  1- dont have an employee_id
        *  2- dont have prep_date
        *  3- dont have dispatch_date
        * -- "dont have" means its null-- */

        fun getAllOrders(): List<Order> {
                val orders = orderRepository.getAllOrders()
                if (orders.isEmpty()) {
                        throw OrderNotFoundException("table 'orders' is empty")
                }

                return orders
        }


        fun getPendingOrders(): List<Order> {
                val pendingOrders = orderRepository.getPendingOrders()
                if (pendingOrders.isEmpty()) {
                        throw PendingOrderNotFoundException("there is no order with an 'id_emp' and 'prep_date' ")
                }

                return pendingOrders
        }

}