package com.application.todoit.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Incorrect checkpoint exception")
public class CheckpointNullException extends RuntimeException {

}
