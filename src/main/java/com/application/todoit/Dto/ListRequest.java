package com.application.todoit.Dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Запрос на изменение или создание списка
 */
@Data
@ApiModel(value = "Список", description = "Данные для изменения или создания списка")
public class ListRequest implements Serializable {

    private String name;

    private String description;

}
