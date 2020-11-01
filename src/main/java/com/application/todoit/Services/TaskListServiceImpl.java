package com.application.todoit.Services;

import com.application.todoit.Dto.*;
import com.application.todoit.DtoTask.TaskResponse;
import com.application.todoit.Exceptions.*;
import com.application.todoit.Interfaces.*;
import com.application.todoit.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Сервис списков
 */
@Service
@Transactional
public class TaskListServiceImpl implements IListService{

    private final ListOfTasksRepository listOfTasksRepository;

    private final TaskRepository taskRepository;

    /**
     * Конструктор для репозиториев
     * @param listOfTasksRepository - репозиторий списков
     * @param taskRepository - репозиторий заданий
     */
    @Autowired
    public TaskListServiceImpl(ListOfTasksRepository listOfTasksRepository, TaskRepository taskRepository) {
        this.listOfTasksRepository = listOfTasksRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public ListsResponse getLists(String sort) {
        List<ListOfTasks> listOfTasks = listOfTasksRepository.findAll();
        ListsResponse listsResponse = new ListsResponse();
        List<TaskListResponse> scheduleLists = new ArrayList<>();
        for (ListOfTasks list : listOfTasks) {
            TaskListResponse newList = new TaskListResponse();
            newList.setId(list.getId());
            newList.setName(list.getName());
            newList.setDescription(list.getDescription());
            newList.setCreationDate(list.getCreationDate());
            newList.setChangeDate(list.getChangeDate());
            scheduleLists.add(newList);
        }

        if ("changeLast".equals(sort)) {
            scheduleLists.sort(new Comparator<TaskListResponse>() {
                @Override
                public int compare(TaskListResponse t, TaskListResponse t1) {
                    return t.getChangeDate().compareTo(t1.getChangeDate());
                }
            });
            Collections.reverse(scheduleLists);
        }
        else if ("changeFirst".equals(sort)) {
            scheduleLists.sort(new Comparator<TaskListResponse>() {
                @Override
                public int compare(TaskListResponse t, TaskListResponse t1) {
                    return t.getChangeDate().compareTo(t1.getChangeDate());
                }
            });
        }

        if ("createLast".equals(sort)) {
            scheduleLists.sort(new Comparator<TaskListResponse>() {
                @Override
                public int compare(TaskListResponse t, TaskListResponse t1) {
                    return t.getCreationDate().compareTo(t1.getCreationDate());
                }
            });
            Collections.reverse(scheduleLists);
        }
        else if ("createFirst".equals(sort)) {
            scheduleLists.sort(new Comparator<TaskListResponse>() {
                @Override
                public int compare(TaskListResponse t, TaskListResponse t1) {
                    return t.getCreationDate().compareTo(t1.getCreationDate());
                }
            });
        }

        if ("nameLast".equals(sort)) {
            scheduleLists.sort(new Comparator<TaskListResponse>() {
                @Override
                public int compare(TaskListResponse t, TaskListResponse t1) {
                    return t.getName().compareToIgnoreCase(t1.getName());
                }
            });
            Collections.reverse(scheduleLists);
        }
        else if ("nameFirst".equals(sort)) {
            scheduleLists.sort(new Comparator<TaskListResponse>() {
                @Override
                public int compare(TaskListResponse t, TaskListResponse t1) {
                    return t.getName().compareToIgnoreCase(t1.getName());
                }
            });
        }
        listsResponse.setLists(scheduleLists);
        return listsResponse;
    }

    @Override
    public FullTaskListResponse getList(UUID listId) throws NotFoundException {
        ListOfTasks taskLists = listOfTasksRepository.findById(listId)
                .orElseThrow(() -> new NotFoundException(String.format("List %s", listId)));
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
        if (listRequest.getName().isEmpty() || listRequest.getName() == null) {
            throw new ThereIsIncorrectNameException();
        }
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
        if (listRequest.getName().isEmpty() || listRequest.getName() == null) {
            throw new ThereIsIncorrectNameException();
        }
        ListOfTasks taskList = listOfTasksRepository.findById(listId)
                .orElseThrow(() -> new NotFoundException(String.format("List %s", listId)));
        taskList.setName(listRequest.getName());
        taskList.setChangeDate(LocalDateTime.now());
        return generateTaskListResponse(taskList);
    }

    @Override
    public void deleteList(UUID listId) throws NotFoundException{
        listOfTasksRepository.findById(listId).orElseThrow(() -> new NotFoundException(String.format("List %s", listId)));
        List<TaskResponse> tasks = new ArrayList<>();
        for (Task x : taskRepository.findAll()) {
            TaskResponse taskResponse = new TaskResponse();
            taskResponse.setListId(x.getListOfTasks().getId());
            taskResponse.setId(x.getId());
            tasks.add(taskResponse);
        }
        for (TaskResponse x : tasks) {
            if (x.getListId().equals(listId)) {
                taskRepository.deleteById(x.getId());
            }
        }
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
