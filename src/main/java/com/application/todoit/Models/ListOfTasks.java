package com.application.todoit.Models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Java-doc
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
