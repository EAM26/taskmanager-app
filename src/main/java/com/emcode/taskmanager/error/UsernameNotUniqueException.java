package com.emcode.taskmanager.error;

public class UsernameNotUniqueException extends RuntimeException{

    public UsernameNotUniqueException(String message) {
        super(message);
    }
}
