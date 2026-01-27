package com.rapidex.rapidex_mobile_api.entities

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empl", nullable = true)
    var employee: Employee? = null,

    @JsonFormat(pattern = "dd-MM-yyyy/HH-mm-ss")
    var prepData: LocalDateTime? = null,

    @JsonFormat(pattern = "dd-MM-yyyy/HH-mm-ss")
    var dispatchData: LocalDateTime? = null
)