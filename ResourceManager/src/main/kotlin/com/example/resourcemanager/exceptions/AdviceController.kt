package com.example.resourcemanager.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class AdviceController {

    @ExceptionHandler(
        AuthorizedUserNotFoundException::class,
        UserNotPermittedException::class
    )
    fun handleForbidden(e: ApplicationException): ResponseEntity<String>{
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.message);
    }

    @ExceptionHandler(
        ArgumentNotGivenException::class,
        InvalidArgumentException::class,
        ResourceNotFoundException::class,
        UserNotFoundException::class
    )
    fun handleBadRequest(e: ApplicationException): ResponseEntity<String>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message);
    }
}