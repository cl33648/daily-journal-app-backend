package com.example.dailyjournalapp.exception;

public class DayNotFoundException extends RuntimeException{
    public DayNotFoundException(String message){
        super(message);
    }
}
