package com.application.todoit.Models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table
@ToString(of = {"idTask", "name"})
@EqualsAndHashCode(of = {"idTask"})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.ShowField.class)
    private UUID idTask;

    @JsonView(Views.ShowField.class)
    private String name;

    @JsonView(Views.ShowField.class)
    private String description;

    @JsonView(Views.ShowField.class)
    private Byte important;

    @JsonView(Views.ShowField.class)
    private Boolean checkpoint;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @JsonView(Views.ShowField.class)
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ListofTasks_ID")
    private ListofTasks listofTasks;

    @JsonView(Views.ShowField.class)
    private UUID getIdList;

    public UUID getGetIdList() {
        return listofTasks.getIdList();
    }

    public void setGetIdList(UUID getIdList) {
        this.getIdList = getIdList;
    }

    public ListofTasks getListofTasks() {
        return listofTasks;
    }

    public void setListofTasks(ListofTasks listofTasks) {
        this.listofTasks = listofTasks;
    }

    public UUID getIdTask() {
        return idTask;
    }

    public void setIdTask(UUID idTask) {
        this.idTask = idTask;
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

    public Byte getImportant() {
        return important;
    }

    public void setImportant(Byte important) {
        this.important = important;
    }

    public Boolean getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(Boolean checkpoint) {
        this.checkpoint = checkpoint;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
