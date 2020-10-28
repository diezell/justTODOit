package com.application.todoit.Dto;

import com.application.todoit.DtoTask.TaskResponse;
import com.application.todoit.Models.Task;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Ответ на запрос получения списка
 */
@Data
public class FullTaskListResponse implements Serializable {

    private UUID id;

    private String name;

    private String description;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime creationDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime changeDate;

    private List<TaskResponse> tasks;

}
