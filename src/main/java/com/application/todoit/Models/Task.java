package com.application.todoit.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

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

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime creationDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime changeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listoftasks_id")
    private ListOfTasks listOfTasks;

}
