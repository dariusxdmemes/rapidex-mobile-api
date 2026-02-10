package com.rapidex.rapidex_mobile_api.controllers

import com.rapidex.rapidex_mobile_api.service.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("rapidex/products")
class ProductWebController(private val productService: ProductService) {

    @GetMapping
    fun listProducts(model: Model): String {
        model.addAttribute("products", productService.getAllProducts())

        return "products/list"
    }

    @GetMapping("/{id}")
    fun productDetail(@PathVariable id: Int, model: Model): String {
        val product = productService.getProductById(id)
        model.addAttribute("product", product)

        return "products/detail"
    }
}