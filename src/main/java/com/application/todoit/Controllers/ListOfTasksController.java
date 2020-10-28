package com.application.todoit.Controllers;

import com.application.todoit.Dto.*;
import com.application.todoit.Exceptions.NotFoundException;
import com.application.todoit.Services.*;
import com.application.todoit.Models.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * java-doc
 */
@RestController
@RequestMapping("/list")
@Validated
public class ListOfTasksController {

    private  final IListService iListService;

    public ListOfTasksController(IListService iListService) {
        this.iListService = iListService;
    }

    @GetMapping
    public ListsResponse getLists() {
        return iListService.getLists();
    }

    @GetMapping("/{id}")                                //актуалочка
    public FullTaskListResponse getList(@PathVariable("id") UUID listId) throws NotFoundException {
        return iListService.getList(listId);
    }

    @PostMapping
    public TaskListResponse createList(@RequestBody @Valid ListRequest taskList) {
        return iListService.createList(taskList);
    }

    @PutMapping("/{id}")
    public TaskListResponse changeList(@RequestBody @Valid ListRequest taskList, @PathVariable("id") UUID listId) throws NotFoundException{
        return iListService.changeList(taskList, listId);
    }

    @DeleteMapping("/{id}")
    public void deleteList(@PathVariable("id") UUID listId) {
        iListService.deleteList(listId);
    }

}
