package com.evo.Task_4_18.service;

import com.evo.Task_4_18.dto.Message;
import com.evo.Task_4_18.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    MessageRepository repository;

    public Message saveMessage(int id, Message message) {
        Message temp = repository.findById(id).orElse(message);
        temp.setText(message.getText());
        temp.setTime(message.getTime());
        temp.setTitle(message.getTitle());
        repository.save(temp);
        return temp;
    }
}
