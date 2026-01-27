package com.rapidex.rapidex_mobile_api.entities

import jakarta.persistence.*

@Entity
@Table(name = "employees")
class Employee (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val fname: String,
    val lname: String
)