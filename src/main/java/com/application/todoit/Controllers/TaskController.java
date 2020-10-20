package com.application.todoit.Controllers;

import com.application.todoit.Interfaces.*;
import com.application.todoit.Models.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("list/{idList}")
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> tasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{idTask}")
    public Task getOne (@PathVariable("idTask") Task task) {
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
