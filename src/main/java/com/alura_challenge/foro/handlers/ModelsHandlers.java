package com.alura_challenge.foro.handlers;

import com.alura_challenge.foro.exceptions.PermissionException;
import com.alura_challenge.foro.exceptions.RoleException;
import com.alura_challenge.foro.exceptions.TopicException;
import com.alura_challenge.foro.exceptions.UserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ModelsHandlers {

    @ExceptionHandler(PermissionException.class)
    public String handlePermissionException(PermissionException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(TopicException.class)
    public String handleTopicException(TopicException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(UserException.class)
    public String handleUserException(UserException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(RoleException.class)
    public String handleRoleException(RoleException exception) {
        return exception.getMessage();
    }

}
