package com.application.todoit.Dto;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * ДТО - ответ на запрос о получении списков
 */
@Data
public class ListsResponse implements Serializable {

    private List<TaskListResponse> lists;

}
