package com.application.todoit.Services;

import com.application.todoit.DtoTask.*;
import com.application.todoit.Exceptions.NotFoundException;
import com.application.todoit.Interfaces.TaskRepository;

import java.util.UUID;

/**
 * Сервис заданий
 */
public interface ITaskService {

    TaskResponse getTask(UUID taskId, UUID listId) throws NotFoundException;

    TaskResponse createTask(CreateTaskRequest createTaskRequest, UUID listId) throws NotFoundException;

    TaskResponse changeTask(ChangeTaskRequest changeTaskRequest, UUID taskId, UUID listId) throws NotFoundException;

    void deleteTask(UUID taskId, UUID listId) throws NotFoundException;
}
