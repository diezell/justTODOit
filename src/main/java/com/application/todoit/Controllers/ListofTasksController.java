package com.application.todoit.Controllers;

import com.application.todoit.Exceptions.*;
import com.application.todoit.Interfaces.*;
import com.application.todoit.Models.*;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * java-doc
 */
@RestController
@RequestMapping("/list")
public class ListofTasksController {

    private final ListofTasksRepository listRepository; // TODO: все действия с репозиториями нужно вынести в сервисы,
    // приинжекченые через интерфейсы - таким образом мы конкретные реализации сможем легко подмерять

    @Autowired
    public ListofTasksController (ListofTasksRepository listRepository) {
        this.listRepository = listRepository;
    }

    @GetMapping
    @JsonView(Views.ShowField.class)
    // TODO: тут и ниже конроллер ничего не должен знать о сущностях модели, соответственно ни принимать, ни отсылать их нельзя!
    public List<ListofTasks> listofTasks () {
        return listRepository.findAll();
    }

    @GetMapping("/{idList}")                                             //different way: public ListofTasks getOne (@PathVariable("idList") UUID responseId) { return this.listRepository.findById(responseId);
    @JsonView(Views.ShowField.class)
    public ListofTasks getOne (@PathVariable("idList") ListofTasks listofTasks) { // TODO: "idList" предполагает что
        // придёт идентификатор, а не целый объект, и опятьже если у нас есть целый объект зачем нам по нему ещё что-то
        // запрашивать
        if (listofTasks == null) {
            throw new ThereIsNoSuchListException();
        }
        return listofTasks;
    }

    @PostMapping
    public ListofTasks create (@RequestBody ListofTasks listofTasks) {// TODO: тоже вамое принимать нужно ДТО и даллее
        if (listofTasks.getName().isEmpty() || listofTasks.getName() == null) {
            throw new ThereIsIncorrectNameException();
        }
        listofTasks.setCreationDate(LocalDateTime.now());
        listofTasks.setChangeDate(LocalDateTime.now());
        return listRepository.save(listofTasks);
    }

    @PutMapping("/{idList}")
    public ListofTasks update (@PathVariable("idList") ListofTasks listFromDb,
            @RequestBody ListofTasks listofTasks) {
        listFromDb.setName(listofTasks.getName());
        listFromDb.setChangeDate(LocalDateTime.now());
        return listRepository.save(listFromDb);
    }

//    @PutMapping("/{idList}")
//    public ListofTasks update (@PathVariable("idList") ListofTasks listFromDb,                                      // listFromDb - получаем из БД
//            @RequestBody ListofTasks listofTasks) {                                                                 // listofTasks - список, который мы получаем от пользователя в виде JSON
//        BeanUtils.copyProperties(listofTasks, listFromDb, "idList", "creationDate", "description");  // этот метод копирует все поля из listofTasks в listFromDb кроме поля id
//        listofTasks.setChangeDate(LocalDateTime.now());
//        return listRepository.save(listFromDb);
//    }

    @DeleteMapping("/{idList}")
    public void delete (@PathVariable("idList") ListofTasks listofTasks) {
        listRepository.delete(listofTasks);
    }

}
