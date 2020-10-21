package com.thoughtworks.basicquiz.exception;

public class UserHasNoEducationException extends RuntimeException {
    public UserHasNoEducationException(String message) {
        super(message);
    }
}
