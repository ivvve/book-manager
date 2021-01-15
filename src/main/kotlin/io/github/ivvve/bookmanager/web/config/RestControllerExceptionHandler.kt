package io.github.ivvve.bookmanager.web.config

import io.github.ivvve.bookmanager.common.exceptions.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice("io.github.ivvve.bookmanager.web")
class RestControllerExceptionHandler {
    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(e: BadRequestException): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DuplicationException::class)
    fun handleDuplicationException(e: DuplicationException): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.CONFLICT)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: EntityNotFoundException): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ExpiredRequestException::class)
    fun handleExpiredRequestException(e: ExpiredRequestException): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(PermissionException::class)
    fun handlePermissionException(e: PermissionException): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(e: UnauthorizedException): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(e: BaseException): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
}