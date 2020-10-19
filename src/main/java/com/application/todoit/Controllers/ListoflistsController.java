package com.application.todoit.Controllers;

import com.application.todoit.Interfaces.ListoflistsRepository;
import com.application.todoit.Models.Listoflists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api")
public class ListoflistsController {

    private final ListoflistsRepository listRepository;

    @Autowired
    public ListoflistsController(ListoflistsRepository listRepository) {
        this.listRepository = listRepository;
    }

    @GetMapping
    public List<Listoflists> listoflists() {
        return listRepository.findAll();
    }

    @GetMapping("{id}")
    public Listoflists getOne (@PathVariable("id") Listoflists listoflists) {
        return listoflists;
    }

    @PostMapping
    public Listoflists create (@RequestBody Listoflists listoflists) {
        return listRepository.save(listoflists);
    }

    @PutMapping("{id}")
    public Listoflists update (@PathVariable("id") Listoflists listFromDb,    // listFromDb - получаем из БД
            @RequestBody Listoflists listoflists) {                           // listoflists - список, который мы получаем от пользователя в виде JSON
        BeanUtils.copyProperties(listoflists, listFromDb, "id");  // этот метод копирует все поля из listoflists в listFromDb кроме поля id
        return listRepository.save(listFromDb);
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable("id") Listoflists listoflists) {
        listRepository.delete(listoflists);
    }

}
