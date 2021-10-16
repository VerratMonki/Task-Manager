package org.monki.taskmanager.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_on")
    private Date updatedDate;

    @Column(name = "state")
    private State state;

    public Message(String text) {this.text = text;}

    public Message() {

    }

    @PrePersist
    public void init(){
        createdDate = new Date(System.currentTimeMillis());
        state = State.PLANNED;
    }

    @PreUpdate
    public void setUpdatedDate(){
        updatedDate = new Date(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", state=" + state +
                '}';
    }
}
