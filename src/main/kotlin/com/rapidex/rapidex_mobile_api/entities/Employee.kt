package com.rapidex.rapidex_mobile_api.entities

import jakarta.persistence.*

@Entity
@Table(name = "employees")
class Employee (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "fname")
    val firstName: String,

    @Column(name = "lname")
    val lastName: String
)