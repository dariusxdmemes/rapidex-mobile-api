package com.rapidex.rapidex_mobile_api.controllers

import com.rapidex.rapidex_mobile_api.service.OrderService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("admin/orders")
class OrderWebController(private val service: OrderService) {
    @GetMapping
    fun showPendingOrders(model: Model): String {
        model.addAttribute("list", service.getPendingOrders())

        return "pendingOrders"
    }
}