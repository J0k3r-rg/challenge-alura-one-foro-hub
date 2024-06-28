package com.alura_challenge.foro.handlers;

import com.alura_challenge.foro.exceptions.PermissionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ModelsHandlers {

    @ExceptionHandler(PermissionException.class)
    public String handlePermissionException(PermissionException exception) {
        return exception.getMessage();
    }

}
