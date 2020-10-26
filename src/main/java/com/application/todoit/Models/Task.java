package com.application.todoit.Models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Java - doc !!! и у полеё тоже
 */
@Entity
@Table
@ToString(of = {"idTask", "name"})
@EqualsAndHashCode(of = {"idTask"})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.ShowField.class)
    private UUID idTask;

    @NotNull
    @JsonView(Views.ShowField.class)
    private String name;

    @JsonView(Views.ShowField.class)
    private String description;

    @JsonView(Views.ShowField.class)
    private Byte important; // TODO: приоритет лучше переделать на перечисление

    @JsonView(Views.ShowField.class)
    private Boolean checkpoint; // TODO: checkpoint - не вполне корректное название поля для "отметка о выполнении",
    // но пусть остаётся, если что быстрый рефакторинг - встаём на поле и жмем shift+F6

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @JsonView(Views.ShowField.class)
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @JsonView(Views.ShowField.class)
    private LocalDateTime changeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ListofTasks_ID") // TODO: поля в базе доблжны быть в формате xxx_jjj_jjj?
    // не нужно смеживать с camelCase!!!
    private ListofTasks listofTasks; // TODO: listOfTasks

    @JsonView(Views.ShowField.class)
    private UUID getIdList; // TODO: Зачем нам это поле? у нас же есть родительский список заданий?
    // от корого мы всегда сможем взять ID&

    // TODO: всё что ниже легко решается через @Getter @Setter lomboka
    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }

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
