package com.application.todoit.Models;

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

    private LocalDateTime creationDate;

    private LocalDateTime changeDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "listOfTasks")
    private List<Task> tasks;

}
