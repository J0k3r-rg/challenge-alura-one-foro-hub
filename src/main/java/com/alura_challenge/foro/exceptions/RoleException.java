package com.alura_challenge.foro.exceptions;

import lombok.Getter;

@Getter
public class RoleException extends Exception{

    public RoleException(String message) {
        super(message);
    }
}
