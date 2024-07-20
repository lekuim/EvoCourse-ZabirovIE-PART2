package com.evo.Task_4_18.dto;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue
    int id;
    String firstname;
    String surname;
    String lastname;
    LocalDate birthday;
    @OneToMany(cascade = CascadeType.ALL)
    List<Message> messages;

    public Person() {
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Person(String firstname, String surname, String lastname, LocalDate birthday, List<Message> messages) {
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.messages = messages;
    }

    public Person(int id, String firstname, String surname, String lastname, LocalDate birthday, List<Message> messages) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.messages = messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void removeMessageById(int id) {
        Message message = messages.stream().filter(m -> m.getId() == id).findFirst().get();
        messages.remove(message);
    }

    public Message getMessageById(int id) {
        return messages.stream().filter(m -> m.getId() == id).findFirst().get();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
