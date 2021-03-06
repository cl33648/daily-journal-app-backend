package com.example.dailyjournalapp.controller;

import com.example.dailyjournalapp.model.Day;
import com.example.dailyjournalapp.service.DayService;
import com.example.dailyjournalapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/day")
public class DayController {
    private final DayService dayService;
    private final TaskService taskService;

    //constructor
    @Autowired
    public DayController(@Qualifier("dayService") DayService dayService, @Qualifier("taskService") TaskService taskService) {
        this.dayService = dayService;
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Day>> getAllDays(){
        List<Day> days = dayService.findAllDays();
        return new ResponseEntity<>(days, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    //With @PathVariable we mark that a method parameter should be retrieved from the requested path.
    public ResponseEntity<Day> getDayById(@PathVariable("id") Long id){

        Day day = dayService.findDayById(id);
        return new ResponseEntity<>(day, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Day> addDay(@RequestBody Day day){

        /*setting the day_id of each task*/
        day.getTask().stream().forEach(task -> task.setDay(day));

        Day newDay = dayService.addDay(day);

        return new ResponseEntity<>(newDay, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Day> updateDay(@RequestBody Day day){
        Day updateDay = dayService.updateDay(day);
        return new ResponseEntity<>(updateDay, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDay(@PathVariable("id") Long id){
        dayService.deleteDay(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
