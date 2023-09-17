package dev.bernouy.cms.common;

import org.springframework.http.HttpStatus;

public class BasicException extends RuntimeException{

    public static final String AUTH_ERROR = "AUTH-01";

    private int status = 400;

    public BasicException(String message) {
        super(message);
    }

    public BasicException(String message, HttpStatus status ){
        super(message);
        this.status = status.value();
    }

    public int getStatus(){
        return this.status;
    }
}
