package com.example.dailyjournalapp.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity //making available to JPA
@Table
public class Day implements Serializable {

    /**
     * To serialize an object means to convert its state to a byte stream
     * so that the byte stream can be reverted back into a copy of the object.
     *
     * @Entity: Day Model saved into Postgre DB table and sent to front end as json
     * @Id and @GeneratedValue:
     * unique ID field for the entity and have its value generated automatically when stored in the database.
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
     *           By including the mappedBy attribute in the Day class, we mark it as the inverse side.
     *           At the same time, we also annotate the Task.day field with @ManyToOne, making Items the owning side
     * orphanRemoval=true: If you remove a Day, it needs to delete all tasks associated with it
     * @JsonManagedReference and @JsonBackReference are designed to handle this two-way linkage between fields,
     * one for Parent role, the other for Child role.
     */
    @JsonManagedReference
    @OneToMany(cascade=ALL, mappedBy="day", orphanRemoval = true)
    //@OneToMany(cascade=ALL, orphanRemoval = true)
    //@JoinColumn(name="id", referencedColumnName = "day_id")
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
