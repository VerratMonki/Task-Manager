package org.monki.taskmanager.repository;

import org.monki.taskmanager.model.Message;
import org.monki.taskmanager.model.State;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface MessageRepository<M> extends CrudRepository<Message, Long> {

    List<Message> findAllByTextContaining(String test);

    @Query(value = "SELECT m FROM Message m WHERE m.createdDate BETWEEN :beginDate AND :endDate")
    List<Message> findMessagesByDateBetween(Date beginDate, Date endDate);

    @Query(value = "SELECT m FROM Message m WHERE m.state = :state")
    List<Message> findMessagesByState(State state);
}


