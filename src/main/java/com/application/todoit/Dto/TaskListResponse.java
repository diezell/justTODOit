package com.application.todoit.Dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * ДТО - ответ на запрос о создании и изменении списка
 */
@Data
public class TaskListResponse implements Serializable {

    private UUID id;

    private String name;

    private String description;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime creationDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime changeDate;


}
