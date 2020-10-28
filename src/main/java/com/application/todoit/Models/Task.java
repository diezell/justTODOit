package com.application.todoit.Models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Сущность задания
 */
@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    private UUID id;

    private String name;

    private String description;

    private Byte important;

    private boolean markDone;

    private LocalDateTime creationDate;

    private LocalDateTime changeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listoftasks_id")
    private ListOfTasks listOfTasks;


}
