package com.rapidex.rapidex_mobile_api.entities

import jakarta.persistence.*

@Entity
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "prod_name")
    var productName: String,

    @Column(name = "prod_cat")
    var productCategory: String,

    @Column(name = "descrip", nullable = true)
    var productDescription: String? = null
)
