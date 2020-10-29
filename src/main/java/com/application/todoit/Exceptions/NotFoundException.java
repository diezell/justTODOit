package com.application.todoit.Exceptions;

/**
 * Исключение для ненайденных запросов
 */
public class NotFoundException extends ApplicationException {

    /**
     * @param message - сообщение исключения
     */
    public NotFoundException(String message) {
        super(message);
    }
}
