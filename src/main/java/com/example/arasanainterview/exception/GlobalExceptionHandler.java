package com.example.arasanainterview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleUserAlreadyExistException(UserAlreadyExistException exception, HttpServletRequest httpServletRequest) {
        return ErrorResponse.builder().message(exception.getMessage()).build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleUserNotFoundException(UserNotFoundException exception, HttpServletRequest httpServletRequest) {
        return ErrorResponse.builder().message(exception.getMessage()).build();
    }
}
