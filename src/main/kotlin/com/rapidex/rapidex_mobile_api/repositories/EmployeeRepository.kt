package com.rapidex.rapidex_mobile_api.repositories

import com.rapidex.rapidex_mobile_api.entities.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Long>