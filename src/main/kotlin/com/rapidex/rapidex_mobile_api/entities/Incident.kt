package com.rapidex.rapidex_mobile_api.entities

import jakarta.persistence.Entity
import jakarta.persistence.Table
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*

@Entity
@Table(name = "incidents")
data class Incident(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    val order: Order,

    @ManyToOne
    @JoinColumn(name = "id_emp", nullable = false)
    val employee: Employee,

    @Column(nullable = false)
    val type: String,

    val descrip: String? = null
)
