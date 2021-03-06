package com.application.todoit.Dto;

import lombok.Data;
import java.io.Serializable;

/**
 * ДТО - запрос на изменение или создание списка
 */
@Data
public class ListRequest implements Serializable {

    private String name;

    private String description;

}
