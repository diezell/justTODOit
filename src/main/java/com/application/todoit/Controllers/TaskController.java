package com.application.todoit.Controllers;

import com.application.todoit.DtoTask.*;
import com.application.todoit.Exceptions.NotFoundException;
import com.application.todoit.Services.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.UUID;

/**
 * Контроллер заданий
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    private final ITaskService iTaskService;

    /**
     * @param iTaskService - репозиторий заданий
     */
    @Autowired
    public TaskController(ITaskService iTaskService) {
        this.iTaskService = iTaskService;
    }

    /**
     * GET-запрос на получение всех заданий по id списка
     * @param listId - id списка
     * @return - возвращает список заданий
     */
    @GetMapping("/ofList/{listId}")
    public TasksResponse getTasks(@PathVariable("listId") UUID listId, String sort, String filter) throws NotFoundException {
        return iTaskService.getTasks(listId, sort, filter);
    }

    /**
     * GET-запрос на получение задания по его id
     * @param taskId - id задания
     * @return - возвращает одно задание
     */
    @GetMapping("/{id}")
    public TaskResponse getTask(@PathVariable("id") UUID taskId) throws NotFoundException {
        return iTaskService.getTask(taskId);
    }

    /**
     * POST-запрос на создание задания в списке
     * @param createTaskRequest - тело запроса с параметрами задания
     * @param listId - id листа, в котором будет создано задание
     * @return - возвращает созданное задание
     */
    @PostMapping("/{listId}")
    public TaskResponse createTask(@RequestBody @Valid CreateTaskRequest createTaskRequest,
            @PathVariable("listId") UUID listId) throws NotFoundException {
        return iTaskService.createTask(createTaskRequest, listId);
    }

    /**
     * PUT-запрос на изменение задания
     * @param changeTaskRequest - тело запроса с параметрами изменений задания
     * @param taskId - id задания, в котором будут изменения
     * @return - возвращает измененное задание
     */
    @PutMapping("/{id}")
    public TaskResponse updateTask(@RequestBody @Valid ChangeTaskRequest changeTaskRequest,
            @PathVariable("id") UUID taskId) throws NotFoundException {
        return iTaskService.changeTask(changeTaskRequest, taskId);
    }

    /**
     * DELETE-запрос на удаление задания
     * @param taskId - id задания, которое нужно удалить
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") UUID taskId) throws NotFoundException {
        iTaskService.deleteTask(taskId);
    }

}
