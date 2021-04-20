package com.example.dailyjournalapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @NotBlank(message = "Task Name cannot be empty or Null")
    private String taskName;

    @Nullable
    private String description;

    /**
     * In case of Many(Task)-One(Day) relationship, the 'Task' owns the relationship
     * and automatically creates the 'day_id' column. This is the JoinColumn.
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "day_id", referencedColumnName = "id")
    private Day day;

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + id +
                ", taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
