package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
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
public ResponseEntity<Void> delateTask(@PathVariable Long taskID) {
    service.deleteTask(taskID);
   return ResponseEntity.ok().build();
}
    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = service.saveTask(task);
        return ResponseEntity.ok(taskMapper.mapToTaskDto(savedTask));
    }
@PostMapping
    public List<TaskDto>  showTaskID(@RequestBody Task task) {
        List<Task> tasks = service.getTaskByID(task);
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        service.saveTask(task);
    }
    /*try {
            return new ResponseEntity<>(taskMapper.mapToTaskDto(service.getTask(taskId)), HttpStatus.OK);
        }catch (TaskNotFoundException e){
            return new ResponseEntity<>(
                    new TaskDto(0l,"That Task: "+taskId,"fail"),HttpStatus.BAD_REQUEST);
        }*/

}
