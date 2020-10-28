package com.application.todoit.Dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Ответ на запрос о получении списков
 */
@Data
public class ListsResponse implements Serializable {

    private List<TaskListResponse> lists;

}
