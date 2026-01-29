package com.rapidex.rapidex_mobile_api.repositories

import com.rapidex.rapidex_mobile_api.entities.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OrderRepository : JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders", nativeQuery = true)
    fun getAllOrders(): List<Order>

    @Query(
        value = """
        SELECT 
            o.id AS orderId,
            p.id AS productId,
            p.prod_name AS productName,
            p.prod_cat AS productCategory
        FROM orders o
        LEFT JOIN products_orders po ON po.id_order = o.id
        LEFT JOIN products p ON p.id = po.id_item
        WHERE o.id_emp IS NULL
          AND o.prep_date IS NULL
          AND o.dispatch_date IS NULL
    """,
        nativeQuery = true
    )
    fun getPendingOrders(): List<Order>


}