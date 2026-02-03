package com.rapidex.rapidex_mobile_api.entities

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToMany
    @JoinTable(
        name = "products_orders",
        joinColumns = [JoinColumn(name = "id_order")],
        inverseJoinColumns = [JoinColumn(name = "id_item")]
    )
    val products: MutableList<Product> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_emp", nullable = true)
    var employee: Employee? = null,

    @Column(name = "prep_date", nullable = true)
    @JsonFormat(pattern = "dd-MM-yyyy/HH-mm-ss")
    var prepDate: LocalDateTime? = null,

    @Column(name = "dispatch_date", nullable = true)
    @JsonFormat(pattern = "dd-MM-yyyy/HH-mm-ss")
    var dispatchDate: LocalDateTime? = null
)