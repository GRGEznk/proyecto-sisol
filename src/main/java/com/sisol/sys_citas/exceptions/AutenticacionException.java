package com.sisol.sys_citas.exceptions;

public class AutenticacionException  extends RuntimeException{

    public AutenticacionException(String message){
        super(message);
    }

    public AutenticacionException(String message, Throwable cause){
        super(message, cause);
    }
    
}
