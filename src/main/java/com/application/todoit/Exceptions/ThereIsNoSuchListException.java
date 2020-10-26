package com.application.todoit.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such list")
public class ThereIsNoSuchListException extends RuntimeException {

}
