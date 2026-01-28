package com.rapidex.rapidex_mobile_api.repositories

import com.rapidex.rapidex_mobile_api.entities.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OrderRepository : JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders WHERE id_emp IS NULL AND prep_date IS NULL AND dispatch_date IS NULL", nativeQuery = true)
    fun getPendingOrders(): List<Order>


}