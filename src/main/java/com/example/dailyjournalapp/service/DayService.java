package com.example.dailyjournalapp.service;

import com.example.dailyjournalapp.exception.DayNotFoundException;
import com.example.dailyjournalapp.model.Day;
import com.example.dailyjournalapp.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Qualifier("dayService")
@Service
public class DayService {
    private final DayRepository dayRepository;

    //constructor
    @Autowired
    public DayService(@Qualifier("dayRepo") DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    public Day addDay(Day day){

        return dayRepository.save(day);

    }

    public List<Day> findAllDays(){
        return dayRepository.findAll();
    }

    public Day updateDay(Day day){
        return dayRepository.save(day);
    }

    @Transactional
    public void deleteDay(Long id){
        dayRepository.deleteDayById(id);
    }

    public Day findDayById(Long id){
        return dayRepository.findDayById(id).orElseThrow(() -> new DayNotFoundException("Day by id "+ id + " not found."));
    }
}
