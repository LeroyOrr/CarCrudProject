package com.leroycode.carcrudproject.exception;

import com.leroycode.carcrudproject.models.APIError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIError> handleRunTimeException(ResourceNotFoundException exception, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        APIError apiError = new APIError(
                LocalDateTime.now().now(),
                status.value(),
                status.name(),
                exception.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
