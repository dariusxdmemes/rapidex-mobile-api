package com.rapidex.rapidex_mobile_api.controllers.web

import com.rapidex.rapidex_mobile_api.model.CreateProductRequestModel
import com.rapidex.rapidex_mobile_api.model.UpdateProductRequestModel
import com.rapidex.rapidex_mobile_api.service.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
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

    @GetMapping("/new")
    fun newProductForm(model: Model): String {
        model.addAttribute("product", CreateProductRequestModel())
        model.addAttribute("categories", productService.getAllProductsCategories())

        return "products/new"
    }

    @PostMapping
    fun createProduct(request: CreateProductRequestModel): String {
        productService.createProduct(request)

        return "redirect:/rapidex/products"
    }

    @GetMapping("/{id}/edit")
    fun editProductForm(@PathVariable id: Int, model: Model): String {
        val product = productService.getProductById(id)

        model.addAttribute("product", product)
        model.addAttribute("categories", productService.getAllProductsCategories())
        model.addAttribute(
            "updateRequest",
            UpdateProductRequestModel(
                productName = product.productName,
                productCategory = product.productCategory,
                productDescription = product.productDescription,
                productImageUrl = product.imageUrl
            )
        )

        return "products/edit"
    }

    @PostMapping("/{id}/edit")
    fun updateProduct(@PathVariable id: Int, request: UpdateProductRequestModel): String {
        productService.updateProduct(id, request)

        return "redirect:/rapidex/products/$id"
    }

    @PostMapping("/{id}/delete")
    fun deleteProduct(@PathVariable id: Int): String {
        productService.deleteProduct(id)

        return "redirect:/rapidex/products"
    }
}