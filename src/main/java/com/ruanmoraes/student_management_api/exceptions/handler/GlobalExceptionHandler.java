package com.ruanmoraes.student_management_api.exceptions.handler;


import com.ruanmoraes.student_management_api.exceptions.ResourceAlreadyCreatedException;
import com.ruanmoraes.student_management_api.exceptions.ResourceNotFoundException;
import com.ruanmoraes.student_management_api.exceptions.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleResourceNotFoundException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date().toString(),
                String.valueOf(404),
                e.getClass().getSimpleName(),
                e.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(404).body(exceptionResponse);
    }

    @ExceptionHandler(ResourceAlreadyCreatedException.class)
    public final ResponseEntity<ExceptionResponse> EnrollmentCreatedAlreadyException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date().toString(),
                String.valueOf(400),
                e.getClass().getSimpleName(),
                e.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(400).body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date().toString(),
                String.valueOf(500),
                e.getClass().getSimpleName(),
                e.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(500).body(exceptionResponse);
    }
}
