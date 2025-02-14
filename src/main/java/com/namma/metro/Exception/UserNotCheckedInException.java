package com.namma.metro.Exception;

public class UserNotCheckedInException extends RuntimeException {
    public UserNotCheckedInException(String message) {
        super(message);
    }
}
