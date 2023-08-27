package com.bdd.cocumber.bdd.service;

import com.bdd.cocumber.bdd.entity.Person;
import com.bdd.cocumber.bdd.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
     List<Person> persons = personRepository.findAll();
        return persons;
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }
}
