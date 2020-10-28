package com.application.todoit.Services;

import com.application.todoit.DtoTask.*;
import com.application.todoit.Exceptions.NotFoundException;
import com.application.todoit.Interfaces.*;
import com.application.todoit.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Реализация TaskService
 */
@Service
@Transactional
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;

    private final ListOfTasksRepository listOfTasksRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ListOfTasksRepository listOfTasksRepository) {
        this.taskRepository = taskRepository;
        this.listOfTasksRepository = listOfTasksRepository;
    }

    @Override
    public TaskResponse getTask(UUID taskId, UUID listId) throws NotFoundException {
        Task task = getAndCheckTask(taskId, listId);
        return generateTaskResponseFromEntity(task);
    }

    @Override
    public TaskResponse createTask(CreateTaskRequest createTaskRequest, UUID listId) throws NotFoundException {
        ListOfTasks listOfTasks = listOfTasksRepository.findById(listId).orElseThrow(() -> new NotFoundException(String.format("List %s", listId)));
        Task task = new Task();
        LocalDateTime time = LocalDateTime.now();
        task.setId(UUID.randomUUID());
        task.setName(createTaskRequest.getName());
        task.setDescription(createTaskRequest.getDescription());
        task.setImportant(createTaskRequest.getImportant());
        task.setMarkDone(false);
        task.setCreationDate(time);
        task.setChangeDate(time);
        task.setListOfTasks(listOfTasks);
        task = taskRepository.save(task);
        return generateTaskResponseFromEntity(task);
    }

    @Override
    public TaskResponse changeTask(ChangeTaskRequest changeTaskRequest, UUID taskId, UUID listId) throws NotFoundException {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isEmpty()) {
            throw new NotFoundException(String.format("Task %s", taskId));
        }
        if (!listOfTasksRepository.existsById(listId)) {
            throw new NotFoundException(String.format("List %s", listId));
        }
        Task task = taskOptional.get();
        task.setMarkDone(changeTaskRequest.isMarkDone());
        task.setChangeDate(LocalDateTime.now());

        UUID oldListId = task.getListOfTasks().getId();
        if (!oldListId.equals(listId)) {
            ListOfTasks newTaskList = listOfTasksRepository.findById(listId).orElseThrow(
                    () -> new NotFoundException(String.format("List %s", listId)));
            ListOfTasks oldTaskList = task.getListOfTasks();
            newTaskList.getTasks().add(task);
            oldTaskList.getTasks().remove(task);
            task.setListOfTasks(newTaskList);
        }
        return generateTaskResponseFromEntity(task);
    }

    @Override
    public void deleteTask(UUID taskId, UUID listId) throws NotFoundException {
        Task task = getAndCheckTask(taskId, listId);
        taskRepository.delete(task);
    }

    private Task getAndCheckTask(UUID taskId, UUID listId) throws NotFoundException {
        ListOfTasks listOfTasks = listOfTasksRepository.findById(listId).orElseThrow(() -> new NotFoundException(String.format("List %s", listId)));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException(String.format("Task %s", taskId)));
        if (!listOfTasks.getTasks().contains(task)) {
            throw new NotFoundException(String.format("Task %s in list %s", taskId, listId));
        }
        return task;
    }

    private static TaskResponse generateTaskResponseFromEntity(Task entity) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(entity.getId());
        taskResponse.setName(entity.getName());
        taskResponse.setDescription(entity.getDescription());
        taskResponse.setImportant(entity.getImportant());
        taskResponse.setCreationDate(entity.getCreationDate());
        taskResponse.setChangeDate(entity.getChangeDate());
        taskResponse.setMarkDone(entity.isMarkDone());
        taskResponse.setListId(entity.getListOfTasks().getId());
        return taskResponse;
    }
}
