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
 * Сервис заданий
 */
@Service
@Transactional
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;

    private final ListOfTasksRepository listOfTasksRepository;

    /**
     * Конструктор для репозиториев
     * @param listOfTasksRepository - репозиторий списков
     * @param taskRepository - репозиторий заданий
     */
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ListOfTasksRepository listOfTasksRepository) {
        this.taskRepository = taskRepository;
        this.listOfTasksRepository = listOfTasksRepository;
    }

    @Override
    public TasksResponse getTasks(UUID listId) throws NotFoundException {
        List<Task> tasks = taskRepository.findAll();
        List<TaskResponse> taskResponse = new ArrayList<>();
        for (Task x : tasks) {
            TaskResponse anyTask = new TaskResponse();
            anyTask.setId(x.getId());
            anyTask.setListId(x.getListOfTasks().getId());
            anyTask.setName(x.getName());
            anyTask.setDescription(x.getDescription());
            anyTask.setImportant(x.getImportant());
            anyTask.setMarkDone(x.isMarkDone());
            anyTask.setCreationDate(x.getCreationDate());
            anyTask.setChangeDate(x.getChangeDate());
            taskResponse.add(anyTask);
        }
        List<TaskResponse> tasksById = new ArrayList<>();
        for (TaskResponse x : taskResponse) {
            if (x.getListId().equals(listId)) {
                tasksById.add(x);
            }
        }
        TasksResponse tasksResponse = new TasksResponse();
        tasksResponse.setTasks(tasksById);
        return tasksResponse;
    }

    @Override
    public TaskResponse getTask(UUID taskId) throws NotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException(String.format("Task %s", taskId)));
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setListId(task.getListOfTasks().getId());
        taskResponse.setName(task.getName());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setImportant(task.getImportant());
        taskResponse.setMarkDone(task.isMarkDone());
        taskResponse.setCreationDate(task.getCreationDate());
        taskResponse.setChangeDate(task.getChangeDate());
        return taskResponse;
    }

    @Override
    public TaskResponse createTask(CreateTaskRequest createTaskRequest, UUID listId) throws NotFoundException {
        ListOfTasks listOfTasks = listOfTasksRepository.findById(listId)
                .orElseThrow(() -> new NotFoundException(String.format("List %s", listId)));
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
    public TaskResponse changeTask(ChangeTaskRequest changeTaskRequest, UUID taskId) throws NotFoundException {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        Task task = taskOptional.get();
        task.setMarkDone(changeTaskRequest.isMarkDone());
        task.setChangeDate(LocalDateTime.now());

        return generateTaskResponseFromEntity(task);
    }

    @Override
    public void deleteTask(UUID taskId) throws NotFoundException {
        taskRepository.deleteById(taskId);
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
