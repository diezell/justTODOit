package com.application.todoit.Services;

import com.application.todoit.Dto.*;
import com.application.todoit.Exceptions.NotFoundException;
import java.util.UUID;

/**
 * Интерфейс сервиса списков
 */
public interface IListService {

    /**
     * Метод для получения всех списков
     */
    ListsResponse getLists();

    /**
     * Метод для получения одного
     */
    FullTaskListResponse getList(UUID listId) throws NotFoundException;

    /**
     * Метода для создания списка
     */
    TaskListResponse createList(ListRequest listRequest);

    /**
     * Метод для изменения списка
     */
    TaskListResponse changeList(ListRequest listRequest, UUID listId) throws NotFoundException;

    /**
     * Метод для удаления списка
     */
    void deleteList(UUID listId);

}
