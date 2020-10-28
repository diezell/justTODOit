package com.application.todoit.DtoTask;

import lombok.Data;

import java.io.Serializable;

/**
 * Запрос на изменение задания
 */
@Data
public class ChangeTaskRequest implements Serializable {

    private boolean markDone;

}
