package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;



 /*   @GetMapping
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }
    @GetMapping(value = "{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) {
        return new TaskDto(1L, "test title", "test_content");
    }

    @DeleteMapping(value = "{taskId}")
    public void deleteTask(@PathVariable Long taskId) {

    }
     @PutMapping
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return new TaskDto(2L, "update TEST", "Updating");
    }
@PostMapping
    public void createTask(@RequestBody TaskDto taskDto) {
    System.out.println(taskDto);
    } */

    @GetMapping
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }
    @PostMapping
    public List<TaskDto>  showTaskID(@RequestBody Task task){
        List<Task> tasks = service.getTaskByID(task);
        return taskMapper.mapToTaskDtoList(tasks);


    }
}