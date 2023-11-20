package dev.bernouy.cms.common.exception;

import org.springframework.http.HttpStatus;

public class MyDatabaseException extends RuntimeException{

    private int status = 505;

    public MyDatabaseException() {
        super("Database Error");
    }

    public MyDatabaseException(String message) {
        super(message);
    }

    public MyDatabaseException(String message, HttpStatus status ){
        super(message);
        this.status = status.value();
    }

    public int getStatus(){
        return this.status;
    }

}
