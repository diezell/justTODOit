package com.application.todoit.Controllers;

import com.application.todoit.Interfaces.ListoflistsRepository;
import com.application.todoit.Models.Listoflists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lists")
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

}
