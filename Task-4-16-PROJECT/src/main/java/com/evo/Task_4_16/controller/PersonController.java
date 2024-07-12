package com.evo.Task_4_16.controller;

import com.evo.Task_4_16.dto.Person;
import com.evo.Task_4_16.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @GetMapping("/person")
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.findById(id);
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
}
