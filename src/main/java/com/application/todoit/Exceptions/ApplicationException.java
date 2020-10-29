package com.application.todoit.Exceptions;

/**
 * Общее исключение для ошибок в приложении
 */
public class ApplicationException extends Exception {

    /**
     * @param message - сообщение исключения
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * @param message - сообщение исключения
     * @param throwable - причина исключения
     */
    public ApplicationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
