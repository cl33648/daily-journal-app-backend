package com.example.dailyjournalapp.repository;

import com.example.dailyjournalapp.model.Day;
import com.example.dailyjournalapp.model.Task;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Repository("taskRepo")
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Task t " +
            "WHERE t.id = ?1")
    void deleteTaskById(Long id);

    @Transactional
    @Query("SELECT t.taskName, t.description " +
            "FROM Task t " +
            "WHERE t.day = ?1")
    Optional<Task> findTaskByDayId(Long dayId);

    Optional<Task> findTaskByDay(Day day);
}
