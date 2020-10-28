package com.application.todoit.Services;

import com.application.todoit.Dto.*;
import com.application.todoit.Exceptions.NotFoundException;

import java.util.UUID;

/**
 * Сервис для спискрв
 */
public interface IListService {

    ListsResponse getLists();

    FullTaskListResponse getList(UUID listId) throws NotFoundException;

    TaskListResponse createList(ListRequest listRequest);

    TaskListResponse changeList(ListRequest listRequest, UUID listId) throws NotFoundException;

    void deleteList(UUID listId);

}
