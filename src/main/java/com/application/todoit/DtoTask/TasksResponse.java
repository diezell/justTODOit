package com.application.todoit.DtoTask;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * ДТО - ответ на запрос о получении задач определенного списка
 */
@Data
public class TasksResponse implements Serializable {

    private List<TaskResponse> tasks;

}
