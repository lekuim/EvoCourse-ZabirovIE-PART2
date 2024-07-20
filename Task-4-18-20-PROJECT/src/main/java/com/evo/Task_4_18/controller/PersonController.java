package com.evo.Task_4_18.controller;

import com.evo.Task_4_18.dto.Person;
import com.evo.Task_4_18.dto.Message;
import com.evo.Task_4_18.repository.MessageRepository;
import com.evo.Task_4_18.repository.PersonRepository;
import com.evo.Task_4_18.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository repository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.findById(id);
    }

    @GetMapping("/person/{p_id}/message")
    public List<Message> getPersonMessages(@PathVariable int p_id) {
        return personService.getPersonMessages(p_id);
    }

    @GetMapping("/person/{p_id}/message/{m_id}")
    public Message getPersonMessageById(@PathVariable int p_id, @PathVariable int m_id) {
        return personService.getPersonMessage(p_id, m_id);
    }

    @PostMapping("/person/{id}/message")
    public ResponseEntity<Person> addMessage(@PathVariable int id, @RequestBody Message message) {
        return personService.addMeesageToPerson(id, message);
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        repository.save(person);
        return person;
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        Person temp = repository.findById(id).orElse(person);
        temp.setFirstname(person.getFirstname());
        temp.setSurname(person.getSurname());
        temp.setLastname(person.getLastname());
        temp.setBirthday(person.getBirthday());
        return new ResponseEntity(repository.save(temp), status);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id) {
        repository.deleteById(id);
    }

    @DeleteMapping("/person/{p_id}/message/{m_id}")
    public void deleteMessageById(@PathVariable int p_id, @PathVariable int m_id) {
        personService.deleteMessageById(p_id, m_id);
    }
}
