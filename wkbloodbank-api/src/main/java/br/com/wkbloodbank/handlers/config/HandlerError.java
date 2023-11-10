package br.com.wkbloodbank.handlers.config;

import br.com.wkbloodbank.handlers.dto.ErrorDTO;
import br.com.wkbloodbank.handlers.exceptions.BadRequestException;
import br.com.wkbloodbank.handlers.exceptions.NotFoundEntityException;
import br.com.wkbloodbank.handlers.exceptions.ServerSideException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerError extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(NotFoundEntityException ex, WebRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDTO errorDescription = new ErrorDTO();
        errorDescription.setStatusCode(status.value());
        errorDescription.setMessage(ex.getMessage());
        errorDescription.setDateTime(LocalDateTime.now());

        return handleExceptionInternal(ex, errorDescription, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException ex, WebRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorDTO errorDescription = new ErrorDTO();
        errorDescription.setMessage(ex.getMessage());
        errorDescription.setDateTime(LocalDateTime.now());
        errorDescription.setStatusCode(status.value());

        return handleExceptionInternal(ex, errorDescription, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ServerSideException.class)
    public ResponseEntity<Object> handleErrorServerSide(ServerSideException ex, WebRequest request){

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorDTO errorDescription = new ErrorDTO();
        errorDescription.setMessage(ex.getMessage());
        errorDescription.setDateTime(LocalDateTime.now());
        errorDescription.setStatusCode(status.value());

        return handleExceptionInternal(ex, errorDescription, new HttpHeaders(), status, request);
    }
}
