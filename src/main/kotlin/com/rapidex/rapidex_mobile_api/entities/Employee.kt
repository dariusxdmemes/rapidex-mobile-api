package com.rapidex.rapidex_mobile_api.entities

import jakarta.persistence.*

@Entity
@Table(name = "employees")
class Employee (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "fname", nullable = false)
    val firstName: String = "",

    @Column(name = "lname", nullable = false)
    val lastName: String = "",

    @Column(name = "username", nullable = false)
    val username: String = "",

    @Column(name = "password", nullable = false)
    val password: String = ""
)