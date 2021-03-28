package com.example.dailyjournalapp.controller;

import com.example.dailyjournalapp.model.Day;
import com.example.dailyjournalapp.service.DayService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day")
public class DayController {
    private final DayService dayService;

    //constructor
    public DayController(@Qualifier("dayService") DayService dayService) {
        this.dayService = dayService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Day>> getAllDays(){
        List<Day> days = dayService.findAllDays();
        return new ResponseEntity<>(days, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Day> getDayById(@PathVariable("id") Long id){

        Day day = dayService.findDayById(id);
        return new ResponseEntity<>(day, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Day> addDay(@RequestBody Day day){
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
