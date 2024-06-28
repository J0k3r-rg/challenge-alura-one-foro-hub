package com.alura_challenge.foro.exceptions;

import lombok.Getter;

@Getter
public class PermissionException extends Exception{

    public PermissionException(String message){
        super(message);
    }

}
