package com.ruanmoraes.student_management_api.exceptions.handler;


import com.ruanmoraes.student_management_api.exceptions.NameConflictException;
import com.ruanmoraes.student_management_api.exceptions.ResourceAlreadyCreatedException;
import com.ruanmoraes.student_management_api.exceptions.ResourceNotFoundException;
import com.ruanmoraes.student_management_api.exceptions.response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@RestController
//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();

        errorResponse.put("errors", errors);

        return errorResponse;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleResourceNotFoundException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date().toString(),
                String.valueOf(HttpStatus.NOT_FOUND),
                e.getClass().getSimpleName(),
                e.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(ResourceAlreadyCreatedException.class)
    public final ResponseEntity<ExceptionResponse> EnrollmentAlreadyCreatedException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date().toString(),
                String.valueOf(HttpStatus.CONFLICT),
                e.getClass().getSimpleName(),
                e.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }

    @ExceptionHandler(NameConflictException.class)
    public final ResponseEntity<ExceptionResponse> NameConflictException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date().toString(),
                String.valueOf(HttpStatus.CONFLICT),
                e.getClass().getSimpleName(),
                e.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .headers(new HttpHeaders())
                .body(getErrorsMap(errors));
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date().toString(),
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR),
                e.getClass().getSimpleName(),
                e.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }
}
