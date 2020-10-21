package com.application.todoit.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table
//@Data
@ToString(of = {"idList", "name"})
@EqualsAndHashCode(of = {"idList"})
public class Listoflists {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Listoflists_ID")
    private Long idList;

    private String name;

    private String description;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime creationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "listoflist")
    private List<Task> tasks = new ArrayList<>();


//    public Listoflists() {
//    }
//
//    public Listoflists(long id, String name, String description) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Long getIdList() {
        return idList;
    }

    public void setIdList(Long idList) {
        this.idList = idList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
