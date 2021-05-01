package com.example.dailyjournalapp.service;

import com.example.dailyjournalapp.exception.TaskNotFoundException;
import com.example.dailyjournalapp.model.Day;
import com.example.dailyjournalapp.model.Task;
import com.example.dailyjournalapp.repository.DayRepository;
import com.example.dailyjournalapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Component
@Service("taskService")
public class TaskService {
    private final TaskRepository taskRepository;

    //constructor
    @Autowired
    public TaskService(@Qualifier("taskRepo") TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks(){
        return taskRepository.findAll();
    }

    public Task updateTask(Task task){
        return taskRepository.save(task);
    }

    public Task addTask(Task task){

        return taskRepository.save(task);

    }

    @Transactional
    public void deleteTask(Long id){
        taskRepository.deleteTaskById(id);
    }

    public Task findTaskByDayId(Long id){
        return taskRepository.findTaskByDayId(id).orElseThrow(() -> new TaskNotFoundException("Task by day id "+ id + " not found."));
    }
}
