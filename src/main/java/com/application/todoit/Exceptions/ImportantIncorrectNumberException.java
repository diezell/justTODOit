package com.application.todoit.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, reason = "Incorrect number exception")
public class ImportantIncorrectNumberException extends RuntimeException {

}
