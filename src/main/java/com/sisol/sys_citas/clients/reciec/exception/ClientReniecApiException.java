package com.sisol.sys_citas.clients.reciec.exception;




public class ClientReniecApiException extends RuntimeException {
    
    
    public ClientReniecApiException(String message){
        super(message);
    }
    
    public ClientReniecApiException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
