package com.application.todoit.DtoTask;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Ответ на запросы о заданиях
 */
@Data
public class TaskResponse implements Serializable {

    private UUID id;

    private UUID listId;

    private String name;

    private String description;

    private Byte important;

    private boolean markDone;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime creationDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime changeDate;
}
