package com.rapidex.rapidex_mobile_api.repositories

import com.rapidex.rapidex_mobile_api.entities.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OrderRepository : JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders", nativeQuery = true)
    fun getAllOrders(): List<Order>

    @Query(
        value = """
    SELECT o FROM Order o 
    LEFT JOIN o.products p 
    WHERE o.employee IS NULL 
      AND o.prepDate IS NULL
""",
        nativeQuery = false
    )
    fun getPendingOrders(): List<Order>


}