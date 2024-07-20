package com.evo.Task_4_18.service;

import com.evo.Task_4_18.dto.Person;
import com.evo.Task_4_18.dto.Message;
import com.evo.Task_4_18.repository.MessageRepository;
import com.evo.Task_4_18.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;

    public ResponseEntity<Person> addMeesageToPerson(int personId, Message message) {
        if (!repository.existsById(personId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Person person = repository.findById(personId).get();
        message.setPerson(person);
        message.setTime(LocalDateTime.now());
        person.addMessage(message);
        return new ResponseEntity<>(repository.save(person), HttpStatus.OK);
    }

    public void deleteMessageById(int p_id, int m_id) {
        Person person = repository.findById(p_id).get();
        person.removeMessageById(m_id);
        repository.save(person);
    }

    public Message getPersonMessage(int p_id, int m_id) {
        Person person = repository.findById(p_id).get();
        return person.getMessageById(m_id);
    }

    public List<Message> getPersonMessages(int id) {
        Person person = repository.findById(id).get();
        return person.getMessages();
    }
}