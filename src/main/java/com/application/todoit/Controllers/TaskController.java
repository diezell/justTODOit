package com.application.todoit.Controllers;

import com.application.todoit.DtoTask.*;
import com.application.todoit.Exceptions.NotFoundException;
import com.application.todoit.Services.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Контроллер для запросов заданий
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    private final ITaskService iTaskService;

    @Autowired
    public TaskController(ITaskService iTaskService) {
        this.iTaskService = iTaskService;
    }

//    @GetMapping
//    @JsonView(Views.ShowField.class)
//    public List<Task> tasks() {
//        return taskRepository.findAll();
//    }

    @GetMapping("/{id}")
    public TaskResponse getTask(@PathVariable("id") UUID taskId, @PathVariable("listId") UUID listId) throws NotFoundException {
        return iTaskService.getTask(taskId, listId);
    }

    @PostMapping
    public TaskResponse createTask(@RequestBody @Valid CreateTaskRequest createTaskRequest,
            @PathVariable("listId") UUID listId) throws NotFoundException {
        return iTaskService.createTask(createTaskRequest, listId);
    }

    @PutMapping("/{id}")               //скорее всего неправильно
    public TaskResponse updateTask(@RequestBody @Valid ChangeTaskRequest changeTaskRequest,
            @PathVariable("id") UUID taskId,
            @PathVariable("listId") UUID listId) throws NotFoundException {
        return iTaskService.changeTask(changeTaskRequest, taskId, listId);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") UUID taskId, @PathVariable("listId") UUID listId) throws NotFoundException {
        iTaskService.deleteTask(taskId, listId);
    }

}
