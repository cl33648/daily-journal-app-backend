package com.example.dailyjournalapp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Day implements Serializable {

    /**
     * To serialize an object means to convert its state to a byte stream
     * so that the byte stream can be reverted back into a copy of the object.
     *
     * @Entity: Day Model saved into Postgre DB table and sent to front end as json
    **/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String createdDate;

    private String todayNotes;

    /**
     * cascade: OneToMany Relationship - entity state transitions should propagate from parent entities to child ones
     *          if Day deleted - all its tasks should be deleted
     * mappedby: the value of mappedBy is the name of the association-mapping attribute on the owning side.
     *           With this, we have now established a bidirectional association between Day and Task entities.
     */
    @OneToMany(cascade=ALL, mappedBy="day")
    private List<Task> task;

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", createdDate='" + createdDate + '\'' +
                ", todayNotes='" + todayNotes + '\'' +
                ", task='" + task + '\'' +
                '}';
    }
}
