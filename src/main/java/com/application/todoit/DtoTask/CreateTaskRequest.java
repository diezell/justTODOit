package com.application.todoit.DtoTask;

import lombok.Data;
import java.io.Serializable;

/**
 * ДТО - запрос на создание задания
 */
@Data
public class CreateTaskRequest implements Serializable {

    private String name;

    private String description;

    private Byte important;

}
