package com.ruanmoraes.student_management_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EnrollmentAlreadyCreatedException extends RuntimeException {
    public EnrollmentAlreadyCreatedException() {
        super("Enrollment already created");
    }
}
