package com.rapidex.rapidex_mobile_api.controller

import com.rapidex.rapidex_mobile_api.controllers.rest.OrderRestController
import com.rapidex.rapidex_mobile_api.exceptions.InternalServerErrorException
import com.rapidex.rapidex_mobile_api.handler.GlobalExceptionHandler
import com.rapidex.rapidex_mobile_api.service.OrderService
import org.springframework.http.MediaType
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.security.test.autoconfigure.webmvc.SecurityMockMvcAutoConfiguration
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Import(GlobalExceptionHandler::class)
@WebMvcTest(
    controllers = [OrderRestController::class],
    excludeAutoConfiguration = [SecurityMockMvcAutoConfiguration::class]
    )
@AutoConfigureMockMvc(addFilters = false)
class OrderControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var orderService: OrderService

    @Test
    fun `should return 500 when service throws exception`() {

        Mockito.`when`(orderService.getAllOrders())
            .thenThrow(InternalServerErrorException("Internal Error"))


        mockMvc.get("/api/orders")
            .andExpect {
                status { isInternalServerError() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.status") { value(500) }
                jsonPath("$.error") { value("Internal Server Error") }
                jsonPath("$.message") { value("Internal Error") }
            }
    }
}