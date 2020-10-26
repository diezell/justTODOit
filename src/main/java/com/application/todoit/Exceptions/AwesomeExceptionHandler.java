package com.application.todoit.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AwesomeExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError(status, "Bad JSON request", ex);
        return new ResponseEntity(apiError, status);
    }



//    @Override
//    protected ResponseEntity<Object> handleNullPointerException(NullPointerException ex,
//            HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ApiError apiError = new ApiError(status, "takovo netu", ex);
//        return new ResponseEntity(apiError, status);
//    }








//    @ExceptionHandler(ThereIsNoSuchListException.class)
//    protected ResponseEntity<AwesomeException> handleThereIsNoSuchUserException() {
//        return new ResponseEntity<>(new AwesomeException("There is no such user"), HttpStatus.NOT_FOUND);
//    }
//
//    @Data
//    @AllArgsConstructor
//    private static class AwesomeException {
//        private String message;
//    }
}