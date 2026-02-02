package com.rapidex.rapidex_mobile_api.handler

import com.rapidex.rapidex_mobile_api.exceptions.BadRequestException
import com.rapidex.rapidex_mobile_api.exceptions.InternalServerErrorException
import com.rapidex.rapidex_mobile_api.exceptions.NotFoundException
import com.rapidex.rapidex_mobile_api.model.ApiError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(InternalServerErrorException::class)
    fun handleInternalServerError(ex: InternalServerErrorException): ResponseEntity<ApiError> {
        val error = ApiError(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            message = ex.message
        )

        return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: NotFoundException): ResponseEntity<ApiError> {
        val error = ApiError(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.reasonPhrase,
            message = ex.message
        )

        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(ex: BadRequestException): ResponseEntity<ApiError>{
        val error = ApiError(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = ex.message
        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}