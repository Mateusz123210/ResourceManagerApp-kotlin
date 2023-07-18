package com.example.resourcemanager.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ApplicationExceptionAdviceController {

    @ExceptionHandler
    fun handleApplicationException(e: ApplicationException):ResponseEntity<String>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message);
    }
}