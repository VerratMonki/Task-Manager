package org.monki.taskmanager.controller;

import org.monki.taskmanager.model.Message;
import org.monki.taskmanager.model.State;
import org.monki.taskmanager.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(messageService.getMessageById(id));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find message with id " + id);
        }
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages(
            @RequestParam(required = false, name = "text") String text,
            @RequestParam(required = false, name = "beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date beginDate,
            @RequestParam(required = false, name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam(required = false, name = "state") State state
    ) {
        if (text != null) {
            return ResponseEntity.ok(messageService.findMessagesByText(text));
        }
        if (beginDate != null && endDate != null) {
            return ResponseEntity.ok(messageService.findMessagesByDateBetween(beginDate, endDate));
        }
        if(state != null) {
            return ResponseEntity.ok(messageService.findMessagesByState(state));
        }
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @PostMapping
    public void createMessage(@Valid @RequestBody Message message) {
        messageService.addMessage(message);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMessage(@PathVariable Long id, @RequestBody @Valid Message message, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        try {
            messageService.updateMessage(id, message);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find message with id " + id);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@PathVariable Long id) {
        try {
            messageService.deleteMessage(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find message with id " + id);
        }
    }

}
