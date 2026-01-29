package com.rapidex.rapidex_mobile_api.handler

import com.rapidex.rapidex_mobile_api.exceptions.OrderNotFoundException
import com.rapidex.rapidex_mobile_api.exceptions.PendingOrderNotFoundException
import com.rapidex.rapidex_mobile_api.model.ApiError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException::class)
    fun handleOrderNotFound(ex: OrderNotFoundException): ResponseEntity<ApiError> {
        val error = ApiError(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.message
        )

        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(PendingOrderNotFoundException::class)
    fun handlePendingOrderNotFound(ex: PendingOrderNotFoundException, request: WebRequest): ResponseEntity<ApiError> {
        val error = ApiError(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.message
        )

        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }
}