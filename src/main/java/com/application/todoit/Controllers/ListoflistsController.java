package com.application.todoit.Controllers;

import com.application.todoit.Interfaces.ListoflistsRepository;
import com.application.todoit.Models.Listoflists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("list")
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

    @GetMapping("/{idList}")
    public Listoflists getOne (@PathVariable("idList") Listoflists listoflists) {
        return listoflists;
    }

    @PostMapping
    public Listoflists create (@RequestBody Listoflists listoflists) {
        listoflists.setCreationDate(LocalDateTime.now());
        return listRepository.save(listoflists);
    }

    @PutMapping("/{idList}")
    public Listoflists update (@PathVariable("idList") Listoflists listFromDb,    // listFromDb - получаем из БД
            @RequestBody Listoflists listoflists) {                           // listoflists - список, который мы получаем от пользователя в виде JSON
        BeanUtils.copyProperties(listoflists, listFromDb, "idList");  // этот метод копирует все поля из listoflists в listFromDb кроме поля id
        return listRepository.save(listFromDb);
    }

    @DeleteMapping("/{idList}")
    public void delete (@PathVariable("idList") Listoflists listoflists) {
        listRepository.delete(listoflists);
    }

}
