package org.monki.taskmanager.loaders;

import org.monki.taskmanager.model.Message;
import org.monki.taskmanager.model.State;
import org.monki.taskmanager.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MessageLoader implements MessageRepository<Message> {
    @Autowired
    private MessageRepository<Message> messageRepository;

    /**
     * Returns a meesage from repository by its id.
     * @param messageId
     * @return a record representing a message
     */
    public Message getMessageById(Long messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(IllegalArgumentException::new);
        return new Message(message.getText());
    }

    @Transactional(readOnly = true)
    public List<Message> getAllMessages() {
        List<Message> actualList = new ArrayList<>();
        messageRepository.findAll().forEach(message -> actualList.add(message));
        return actualList;
    }

    public List<Message> findMessagesByText(String text) {
        return messageRepository.findAllByTextContaining(text);
    }

    @Override
    public List<Message> findAllByTextContaining(String test) {
        return null;
    }

    @Override
    public List<Message> findMessagesByDateBetween(Date beginDate, Date endDate) {
        return messageRepository.findMessagesByDateBetween(beginDate, endDate);
    }

    @Override
    public List<Message> findMessagesByState(State state) {
        return messageRepository.findMessagesByState(state);
    }

    @Override
    public <S extends Message> S save(S entity) {
        entity = messageRepository.save(entity);
        return (S) entity;
    }

    @Override
    public <S extends Message> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Message> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Message> findAllById(Iterable<Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        messageRepository.deleteById(aLong);
    }

    @Override
    public void delete(Message entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends Message> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
