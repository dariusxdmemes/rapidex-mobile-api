package com.rapidex.rapidex_mobile_api.handler

import com.rapidex.rapidex_mobile_api.exceptions.InternalServerErrorException
import com.rapidex.rapidex_mobile_api.model.ApiError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    /* todo Errors regarding orders & orders/pending:
    *   - this two endpoints should ONLY handle '500'
    *   - this two endpoints should NOT handle '404'
    *   - if the tables have no data, then return is '200' */

    @ExceptionHandler(InternalServerErrorException::class)
    fun handleInternalServerError(ex: InternalServerErrorException): ResponseEntity<ApiError> {
        val error = ApiError(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            message = ex.message
        )

        return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}