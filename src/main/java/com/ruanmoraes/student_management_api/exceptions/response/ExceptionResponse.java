package com.ruanmoraes.student_management_api.exceptions.response;

public record ExceptionResponse(
        String timestamp,
        String error,
        String message,
        String path) {
}
