package org.monki.taskmanager.service;

import org.monki.taskmanager.loaders.MessageLoader;
import org.monki.taskmanager.model.Message;
import org.monki.taskmanager.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageLoader messageLoader;

    public void addMessage(Message newMessage) {
        Message message = new Message(newMessage.getText());
        messageLoader.save(message);
    }

    public Message updateMessage(Long messageId, Message updatedMessage) {
        Message message = messageLoader.findById(messageId).orElseThrow(IllegalArgumentException::new);
        if(State.getAllowedStates(message.getState()).contains(updatedMessage.getState())) {
            message.setText(updatedMessage.getText());
            message.setState(updatedMessage.getState());
            messageLoader.save(message);
        }
        return message;
    }

    public void deleteMessage(Long messageId) {
        messageLoader.deleteById(messageId);
    }

    public Message getMessageById(Long id) {
        return messageLoader.getMessageById(id);
    }

    public List<Message> findMessagesByText(String text) {
        return messageLoader.findMessagesByText(text);
    }

    public List<Message> findMessagesByDateBetween(Date beginDate, Date endDate) {
        return messageLoader.findMessagesByDateBetween(beginDate, endDate);
    }

    public List<Message> findMessagesByState(State state) {
        return messageLoader.findMessagesByState(state);
    }

    public List<Message> getAllMessages() {
        return messageLoader.getAllMessages();
    }
}
