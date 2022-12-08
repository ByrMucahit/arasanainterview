package com.example.arasanainterview.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class UserNotFoundException extends RuntimeException  {
    public UserNotFoundException(String key, String message) {
        super(String.format("key: %s, message: %s",key,message));
    }
}
