package com.example.arasanainterview.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(String.format(message));
    }
}
