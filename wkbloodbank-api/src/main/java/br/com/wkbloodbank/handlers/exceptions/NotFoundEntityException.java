package br.com.wkbloodbank.handlers.exceptions;

public class NotFoundEntityException extends RuntimeException{
    public NotFoundEntityException(String message){
        super(message);
    }
}
