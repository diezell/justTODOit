package com.application.todoit.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * java-doc
 */
@ResponseStatus(code = HttpStatus.LENGTH_REQUIRED, reason = "IncorrectNameException")        //какой HttpStatus ????
public class ThereIsIncorrectNameException extends RuntimeException{

}
