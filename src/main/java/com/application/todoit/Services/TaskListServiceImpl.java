package com.application.todoit.Services;


import com.application.todoit.Dto.*;
import com.application.todoit.Exceptions.NotFoundException;
import com.application.todoit.Interfaces.*;
import com.application.todoit.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Реализация TaskListService
 */
@Service
@Transactional
public class TaskListServiceImpl implements IListService{

    private final ListOfTasksRepository listOfTasksRepository;

    private final TaskRepository taskRepository;

    @Autowired
    public TaskListServiceImpl(ListOfTasksRepository listOfTasksRepository, TaskRepository taskRepository) {
        this.listOfTasksRepository = listOfTasksRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public ListsResponse getLists() {
        ListsResponse listsResponse = new ListsResponse();
        return listsResponse;
    }

    @Override
    public FullTaskListResponse getList(UUID listId) throws NotFoundException {
        ListOfTasks taskLists = listOfTasksRepository.findById(listId).orElseThrow(() -> new NotFoundException(String.format("List %s", listId)));
        FullTaskListResponse fullTaskListResponse = new FullTaskListResponse();
        fullTaskListResponse.setId(taskLists.getId());
        fullTaskListResponse.setName(taskLists.getName());
        fullTaskListResponse.setDescription(taskLists.getDescription());
        fullTaskListResponse.setCreationDate(taskLists.getCreationDate());
        fullTaskListResponse.setChangeDate(taskLists.getChangeDate());
        return fullTaskListResponse;
    }

    @Override
    public TaskListResponse createList(ListRequest listRequest) {
        ListOfTasks taskList = new ListOfTasks();
        LocalDateTime time = LocalDateTime.now();
        taskList.setId(UUID.randomUUID());
        taskList.setName(listRequest.getName());
        taskList.setDescription(listRequest.getDescription());
        taskList.setCreationDate(time);
        taskList.setChangeDate(time);
        listOfTasksRepository.save(taskList);
        return generateTaskListResponse(taskList);
    }

    @Override
    public TaskListResponse changeList(ListRequest listRequest, UUID listId) throws NotFoundException{
        ListOfTasks taskList = listOfTasksRepository.findById(listId).orElseThrow(() -> new NotFoundException(String.format("List %s", listId)));
        taskList.setName(listRequest.getName());
        taskList.setChangeDate(LocalDateTime.now());
        return generateTaskListResponse(taskList);
    }

    @Override
    public void deleteList(UUID listId) {
        listOfTasksRepository.deleteById(listId);
    }

    private static TaskListResponse generateTaskListResponse(ListOfTasks entity) {
        TaskListResponse taskListResponse = new TaskListResponse();
        taskListResponse.setId(entity.getId());
        taskListResponse.setName(entity.getName());
        taskListResponse.setDescription(entity.getDescription());
        taskListResponse.setCreationDate(entity.getCreationDate());
        taskListResponse.setChangeDate(entity.getChangeDate());
        return taskListResponse;
    }
}
