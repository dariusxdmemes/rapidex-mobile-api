package com.rapidex.rapidex_mobile_api.repositories

import com.rapidex.rapidex_mobile_api.entities.Incident
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface IncidentRepository : JpaRepository<Incident, Int> {

    fun findByEmployeeId(id: Long): List<Incident>
}