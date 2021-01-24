package com.ibtardis.patient.api.exceptions;

public class RecordAlreadyExistsException extends RuntimeException{

    public RecordAlreadyExistsException(String message){
        super(message);
    }
}
