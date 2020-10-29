package com.application.todoit.Controllers;

import com.application.todoit.Dto.*;
import com.application.todoit.Exceptions.NotFoundException;
import com.application.todoit.Services.IListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.UUID;

/**
 * Контроллер списка
 */
@RestController
@RequestMapping("/list")
@Validated
public class ListOfTasksController {

    private  final IListService iListService;

    /**
     * Репозиторий списка
     */
    @Autowired
    public ListOfTasksController(IListService iListService) {
        this.iListService = iListService;
    }

    /**
     * GET-запрос на получение всех списков
     * @return - возвращает все списки
     */
    @GetMapping
    public ListsResponse getLists() {
        return iListService.getLists();
    }

    /**
     * GET-запрос на получение списка по id
     * @param listId - id запрашиваемого списка
     * @return - возвращает список по id
     */
    @GetMapping("/{id}")
    public FullTaskListResponse getList(@PathVariable("id") UUID listId) throws NotFoundException {
        return iListService.getList(listId);
    }

    /**
     * POST-запрос на создание списка
     * @param taskList - тело запроса с параметрами списка
     * @return - возвращает созданный список
     */
    @PostMapping
    public TaskListResponse createList(@RequestBody @Valid ListRequest taskList) {
        return iListService.createList(taskList);
    }

    /**
     * PUT-запрос на изменение списка по id
     * @param taskList -тело запроса с параметрами изменения списка
     * @param listId - id списка, в котором будут изменения
     * @return - возвращает измененный список
     */
    @PutMapping("/{id}")
    public TaskListResponse changeList(@RequestBody @Valid ListRequest taskList,
            @PathVariable("id") UUID listId) throws NotFoundException{
        return iListService.changeList(taskList, listId);
    }

    /**
     * DELETE-запрос на удаление списка по id
     * @param listId - id удаляемого списка
     */
    @DeleteMapping("/{id}")
    public void deleteList(@PathVariable("id") UUID listId) {
        iListService.deleteList(listId);
    }

}
