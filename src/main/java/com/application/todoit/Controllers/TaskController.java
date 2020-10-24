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
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    @JsonView(Views.ShowField.class)
    public List<Task> tasks() {

        return taskRepository.findAll();
    }

    @GetMapping("/{idTask}")
    @JsonView(Views.ShowField.class)
    public Task getOne (@PathVariable("idTask") Task task) {
        task.getGetIdList();
        return task;
    }

    @PostMapping
    public Task create (@RequestBody Task task) {
        task.setCreationDate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @PutMapping("/{idTask}")
    public Task update (@PathVariable("idTask") Task taskFromDb,
            @RequestBody Task task) {
        BeanUtils.copyProperties(task, taskFromDb, "idTask");
        return taskRepository.save(taskFromDb);
    }

    @DeleteMapping("/{idTask}")
    public void delete (@PathVariable("idTask") Task task) {
        taskRepository.delete(task);
    }

}
