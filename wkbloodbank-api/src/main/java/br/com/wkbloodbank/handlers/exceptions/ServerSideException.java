package br.com.wkbloodbank.handlers.exceptions;

public class ServerSideException extends RuntimeException{
    public ServerSideException(String message){
        super(message);
    }
}
