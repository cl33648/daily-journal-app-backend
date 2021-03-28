package com.example.dailyjournalapp.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long taskId;

    @NotBlank(message = "Task Name cannot be empty or Null")
    private String taskName;

    @Nullable
    private String description;

    /**
     * In case of Many(Task)-One(Day) relationship, the 'Task' owns the relationship
     * and automatically creates the 'day_id' column. This is the JoinColumn.
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "day_id")
    private Day day;

}
