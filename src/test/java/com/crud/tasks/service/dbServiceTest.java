package com.crud.tasks.service;


import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class dbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    void saveTaskTest(){
        //Given
        Task task1 = new Task(1L,"cos","cos");
        when(taskRepository.save(task1)).thenReturn(task1);
        //When
        Task result = dbService.saveTask(task1);
        //Then
        assertEquals(task1.getId(),result.getId());
        assertEquals(task1.getContent(), result.getContent());
        assertEquals(task1.getTitle(), result.getTitle());

    }

    @Test
    void getTaskTest() throws Exception{
        //Given
        Task task1 = new Task(1L,"title","text");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
        //When
        Task result = dbService.getTask(1L);
        //Then
        assertEquals(1L,result.getId());
        assertEquals("title",result.getTitle());
        assertEquals("text",result.getContent());
    }

    @Test
    void getAllTasks(){
        //Given
        Task task1 = new Task(1L,"title1","1");
        Task task2 = new Task(2L,"title2","2");
        List<Task> list = new ArrayList<>();
        list.add(task1);
        list.add(task2);
        when(taskRepository.findAll()).thenReturn(list);
        //When
        List<Task> result = dbService.getAllTasks();
        //Then
        assertEquals(2,result.size());
        for (Task task : result){
            System.out.println("ID: " + task.getId() + " Title: " + task.getTitle() + " Con: " + task.getContent());
        }
    }
}