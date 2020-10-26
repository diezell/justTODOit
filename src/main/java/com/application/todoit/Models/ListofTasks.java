package com.application.todoit.Models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Java-doc
 */
@Entity
@Table
@ToString(of = {"idList", "name"})
@EqualsAndHashCode(of = {"idList"})
public class ListofTasks { // TODO: camelCase с большой буквы ListOfTasks

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ListofTasks_ID")
    @JsonView(Views.ShowField.class)
    private UUID idList;

    @NotNull
    @JsonView(Views.ShowField.class)
    private String name;

    @JsonView(Views.ShowField.class)
    private String description;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @JsonView(Views.ShowField.class)
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @JsonView(Views.ShowField.class)
    private LocalDateTime changeDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "listofTasks")
    private List<Task> tasks = new ArrayList<>();

    // TODO: всё что ниже легко решается через @Getter @Setter lomboka
    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }

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
