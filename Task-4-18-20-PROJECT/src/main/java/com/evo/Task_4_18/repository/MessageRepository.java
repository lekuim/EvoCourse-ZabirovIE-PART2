package com.evo.Task_4_18.repository;

import com.evo.Task_4_18.dto.Message;
import com.evo.Task_4_18.dto.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
}
