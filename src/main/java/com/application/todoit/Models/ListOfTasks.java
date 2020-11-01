package com.application.todoit.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Сущность списка
 */
@Data
@Entity
@Table(name = "list_of_tasks")
public class ListOfTasks {

    @Id
    @Column(name = "listoftasks_id")
    private UUID id;

    private String name;

    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime creationDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime changeDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "listOfTasks")
    private List<Task> tasks;

}
