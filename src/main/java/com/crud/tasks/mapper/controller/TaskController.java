package com.crud.tasks.mapper.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.service.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;

@GetMapping
public ResponseEntity<List<TaskDto>> getTasks(){
    List<Task> tasks = service.getAllTasks();
    return ResponseEntity.ok(taskMapper.mapToTaskDtoList(tasks));
}
@GetMapping(value = "{taskID}")
public ResponseEntity<TaskDto>getTask(@PathVariable Long taskID) throws TaskNotFoundException{
    return ResponseEntity.ok(taskMapper.mapToTaskDto(service.getTask(taskID)));
}

@DeleteMapping(value = "{taskID}")
public ResponseEntity<Void> deleteTask(@PathVariable Long taskID) {
    service.deleteTask(taskID);
   return ResponseEntity.ok().build();
}
    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = service.saveTask(task);
        return ResponseEntity.ok(taskMapper.mapToTaskDto(savedTask));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        service.saveTask(task);
    }

}
