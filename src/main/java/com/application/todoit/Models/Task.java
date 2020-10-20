package com.application.todoit.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@ToString(of = {"idTask", "name"})
@EqualsAndHashCode(of = {"idTask"})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTask;

    private String name;

    private String description;

    private Byte important;

    private Boolean checkpoint;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime creationDate;

    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
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
