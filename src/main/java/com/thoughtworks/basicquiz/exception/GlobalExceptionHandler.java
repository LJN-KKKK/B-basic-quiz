package com.thoughtworks.basicquiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserHasNoEducationException.class, UserNotExistException.class})
    public ResponseEntity<ErrorResult> handle(RuntimeException ex) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ", Locale.CHINA);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        ErrorResult errorResult = new ErrorResult(
                sdf.format(new Date()),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException ex) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ", Locale.CHINA);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String message = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        ErrorResult errorResult = new ErrorResult(
                sdf.format(new Date()),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
