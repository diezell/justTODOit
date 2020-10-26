package com.application.todoit.Controllers;

import com.application.todoit.Exceptions.*;
import com.application.todoit.Interfaces.*;
import com.application.todoit.Models.*;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Java-doc
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    // TODO: замечения теже что и в другом контроллере - действия с репой вынести в сервис, сушности модели убрать и заменить на ДТО

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController (TaskRepository taskRepository) {
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
    @JsonView(Views.ShowField.class)
    public Task create (@RequestBody Task task) {
        if (task.getImportant() != null && task.getImportant() < 1 || task.getImportant() != null && task.getImportant() > 5) {
            throw new ImportantIncorrectNumberException();
        }
        task.setCreationDate(LocalDateTime.now());
        task.setChangeDate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @PutMapping("/{idTask}")
    @JsonView(Views.ShowField.class)
    public Task update (@PathVariable("idTask") Task taskFromDb,                       //если прилетают 2 параметра, возможно поможет if()
            @RequestBody Task task) {
        if (task.getCheckpoint() == null) {
            throw new CheckpointNullException();
        }
        else {
        taskFromDb.setCheckpoint(task.getCheckpoint());
        taskFromDb.setChangeDate(LocalDateTime.now());
        return taskRepository.save(taskFromDb);
        }
    }

    @DeleteMapping("/{idTask}")
    public void delete (@PathVariable("idTask") Task task) {
        taskRepository.delete(task);
    }

}
