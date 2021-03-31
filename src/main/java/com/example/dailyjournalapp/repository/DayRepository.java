package com.example.dailyjournalapp.repository;

import com.example.dailyjournalapp.model.Day;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Repository("dayRepo")
public interface DayRepository extends JpaRepository<Day, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Day d " +
            "WHERE d.id = ?1")
    void deleteDayById(Long id);

    Optional<Day> findDayById(Long id);
}
