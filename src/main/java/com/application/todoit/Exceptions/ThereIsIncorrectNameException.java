package com.application.todoit.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение некорректно введенного имени
 */
@ResponseStatus(code = HttpStatus.LENGTH_REQUIRED, reason = "IncorrectNameException")
public class ThereIsIncorrectNameException extends RuntimeException{

}