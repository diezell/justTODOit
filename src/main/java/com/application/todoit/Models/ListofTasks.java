package com.application.todoit.Models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table
@ToString(of = {"idList", "name"})
@EqualsAndHashCode(of = {"idList"})
public class ListofTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ListofTasks_ID")
    @JsonView(Views.ShowField.class)
    private UUID idList;

    @JsonView(Views.ShowField.class)
    private String name;

    @JsonView(Views.ShowField.class)
    private String description;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @JsonView(Views.ShowField.class)
    private LocalDateTime creationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "listofTasks")
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public UUID getIdList() {
        return idList;
    }

    public void setIdList(UUID idList) {
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
