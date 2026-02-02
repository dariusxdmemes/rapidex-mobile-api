package com.rapidex.rapidex_mobile_api.exceptions

class InternalServerErrorException(message: String): RuntimeException(message)
class NotFoundException(message: String): RuntimeException(message)
class BadRequestException(message: String): RuntimeException(message)
