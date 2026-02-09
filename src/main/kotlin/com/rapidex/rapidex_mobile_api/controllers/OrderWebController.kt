package com.rapidex.rapidex_mobile_api.controllers

import com.rapidex.rapidex_mobile_api.model.CreateOrderRequestModel
import com.rapidex.rapidex_mobile_api.service.OrderService
import com.rapidex.rapidex_mobile_api.service.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("rapidex/orders")
class OrderWebController(private val orderService: OrderService, private val productService: ProductService) {
    @GetMapping
    fun showPendingOrders(model: Model): String {
        model.addAttribute("orders", orderService.getAllOrders())

        return "orders/list"
    }

    @GetMapping("/{id}")
    fun orderDetail(@PathVariable id: Int, model: Model): String {
        val order = orderService.getOrderById(id)
        model.addAttribute("order", order)

        return "orders/detail"
    }

    @GetMapping("/new")
    fun newOrderForm(model: Model): String {
        model.addAttribute("products", productService.getAllProducts())
        model.addAttribute("order", CreateOrderRequestModel())

        return "orders/new"
    }

    @PostMapping
    fun createOrder(request: CreateOrderRequestModel): String {
        orderService.createOrder(request)

        return "redirect:/rapidex/orders"
    }
}