package com.rapidex.rapidex_mobile_api.service

import com.rapidex.rapidex_mobile_api.entities.Product
import com.rapidex.rapidex_mobile_api.exceptions.BadRequestException
import com.rapidex.rapidex_mobile_api.exceptions.NotFoundException
import com.rapidex.rapidex_mobile_api.model.CreateProductRequestModel
import com.rapidex.rapidex_mobile_api.model.UpdateProductRequestModel
import com.rapidex.rapidex_mobile_api.repositories.OrderRepository
import com.rapidex.rapidex_mobile_api.repositories.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository, private val orderRepository: OrderRepository) {
    fun getAllProducts(): List<Product> = productRepository.findAll()

    fun getProductById(productId: Int): Product = productRepository.findById(productId)
        .orElseThrow { NotFoundException("Product not found") }

    fun getProductsByOrderId(orderId: Int): List<Product> {
        val order = orderRepository.findById(orderId)
            .orElseThrow { NotFoundException("Order not found") }

        val products = order.products.toList()

        return products
    }

    fun getAllProductsCategories(): List<String> = productRepository.findAllCategories()

    fun createProduct(request: CreateProductRequestModel) {
        val product = Product(
            productName = request.productName,
            productCategory = request.productCategory,
            productDescription = request.productCategory,
            imageUrl = request.productImageUrl
        )

        productRepository.save(product)
    }

    fun updateProduct(productId: Int, request: UpdateProductRequestModel) {
        val product = productRepository.findById(productId)
            .orElseThrow { NotFoundException("Product not found") }

        product.productName = request.productName
        product.productCategory = request.productCategory
        product.productDescription = request.productDescription
        product.imageUrl = request.productImageUrl

        productRepository.save(product)
    }

    fun deleteProduct(productId: Int) {
        if (!productRepository.existsById(productId)) {
            throw BadRequestException("Product not found")
        }

        productRepository.deleteById(productId)
    }
}