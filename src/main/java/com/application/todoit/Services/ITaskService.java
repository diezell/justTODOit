package com.application.todoit.Services;

import com.application.todoit.DtoTask.*;
import com.application.todoit.Exceptions.NotFoundException;
import java.util.UUID;

/**
 * Интерфейс сериса заданий
 */
public interface ITaskService {

    /**
     * Метод для получения всех заданий определенного списка
     */
    TasksResponse getTasks(UUID listId, String sort, String filter) throws NotFoundException;

    /**
     * Метод для получения одного задания
     */
    TaskResponse getTask(UUID taskId) throws NotFoundException;

    /**
     * Метод для создания задания
     */
    TaskResponse createTask(CreateTaskRequest createTaskRequest, UUID listId) throws NotFoundException;

    /**
     * Метод для изменения задания
     */
    TaskResponse changeTask(ChangeTaskRequest changeTaskRequest, UUID taskId) throws NotFoundException;

    /**
     * Метод для удаления задания
     */
    void deleteTask(UUID taskId) throws NotFoundException;

}
