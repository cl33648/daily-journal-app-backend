package com.example.dailyjournalapp.service;

import com.example.dailyjournalapp.exception.DayNotFoundException;
import com.example.dailyjournalapp.model.Day;
import com.example.dailyjournalapp.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Component
@Service("dayService")
public class DayService {
    private final DayRepository dayRepository;

    //constructor
    @Autowired
    public DayService(@Qualifier("dayRepo") DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    public Day addDay(Day day){

        //set current date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String now = formatter.format(calendar.getTime());

        day.setCreatedDate(now);

        //making sure associated day_id gets populated on task table
        //if(day.getTask()!=null) day.getTask().stream().forEach(b->{ b.setDay(day); });

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
