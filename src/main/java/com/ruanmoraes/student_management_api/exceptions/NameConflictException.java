package com.ruanmoraes.student_management_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NameConflictException extends RuntimeException {
    public NameConflictException(String name) {
        super("Discipline with name " + name + " already exists.");
    }
}
