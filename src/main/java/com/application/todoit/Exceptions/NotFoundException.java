package com.application.todoit.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение для ненайденных запросов
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such page")
public class NotFoundException extends ApplicationException {

    /**
     * @param message - сообщение исключения
     */

    public NotFoundException(String message) {
        super(message);
    }
}
