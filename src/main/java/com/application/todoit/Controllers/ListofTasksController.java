package com.application.todoit.Controllers;

import com.application.todoit.Interfaces.*;
import com.application.todoit.Models.*;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/list")
public class ListofTasksController {

    private final ListofTasksRepository listRepository;

    @Autowired
    public ListofTasksController (ListofTasksRepository listRepository) {
        this.listRepository = listRepository;
    }

    @GetMapping
    @JsonView(Views.ShowField.class)
    public List<ListofTasks> listofTasks () {
        return listRepository.findAll();
    }

    @GetMapping("/{idList}")
    @JsonView(Views.ShowField.class)
    public ListofTasks getOne (@PathVariable("idList") ListofTasks listofTasks) {
        return listofTasks;
    }

    @PostMapping
    public ListofTasks create (@RequestBody ListofTasks listofTasks) {
        listofTasks.setCreationDate(LocalDateTime.now());
        return listRepository.save(listofTasks);
    }

    @PutMapping("/{idList}")
    public ListofTasks update (@PathVariable("idList") ListofTasks listFromDb,       // listFromDb - получаем из БД
            @RequestBody ListofTasks listofTasks) {                                  // listofTasks - список, который мы получаем от пользователя в виде JSON
        BeanUtils.copyProperties(listofTasks, listFromDb, "idList");  // этот метод копирует все поля из listofTasks в listFromDb кроме поля id
        return listRepository.save(listFromDb);
    }

    @DeleteMapping("/{idList}")
    public void delete (@PathVariable("idList") ListofTasks listofTasks) {
        listRepository.delete(listofTasks);
    }

}