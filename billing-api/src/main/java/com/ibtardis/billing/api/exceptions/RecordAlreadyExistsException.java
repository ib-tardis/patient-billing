package com.ibtardis.billing.api.exceptions;

public class RecordAlreadyExistsException extends RuntimeException{

    public RecordAlreadyExistsException(String message){
        super(message);
    }
}
