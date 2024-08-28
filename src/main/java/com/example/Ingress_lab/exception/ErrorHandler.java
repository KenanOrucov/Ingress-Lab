package com.example.Ingress_lab.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.Ingress_lab.model.enums.ExceptionConstants.HTTP_METHOD_NOT_ALLOWED;
import static com.example.Ingress_lab.model.enums.ExceptionConstants.UNEXPECTED_EXCEPTION;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception ex){
        log.error("Exception {}", ex);
        return new ErrorResponse(UNEXPECTED_EXCEPTION.getCode(), UNEXPECTED_EXCEPTION.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public ErrorResponse handle(HttpRequestMethodNotSupportedException ex){
        log.error("Exception {}", ex);
        return new ErrorResponse(HTTP_METHOD_NOT_ALLOWED.getCode(), HTTP_METHOD_NOT_ALLOWED.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException ex){
        log.error("NotFoundException {}", ex);
        return new ErrorResponse(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(GuideBusyException.class)
    @ResponseStatus(NOT_ACCEPTABLE)
    public ErrorResponse handle(GuideBusyException ex){
        log.error("GuideBusyException {}", ex);
        return new ErrorResponse(ex.getCode(), ex.getMessage());
    }
}
