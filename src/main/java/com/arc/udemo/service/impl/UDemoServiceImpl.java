package com.arc.udemo.service.impl;

import com.arc.udemo.domain.Person;
import com.arc.udemo.repository.PersonRepository;
import com.arc.udemo.service.UDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public class UDemoServiceImpl implements UDemoService {

    private PersonRepository personRepository;

    @Autowired
    public UDemoServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Person findPersonById(int id) throws DataAccessException {
        Person person = null;
        try {
            person = personRepository.findById(id);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e) {
            // just ignore not found exceptions for Jdbc/Jpa realization
            return null;
        }
        return person;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Person> findAllPerson() throws DataAccessException {
        return personRepository.findAll();
    }

    @Override
    @Transactional
    public void savePerson(Person person) throws DataAccessException {
        personRepository.save(person);
    }

    @Override
    @Transactional
    public void deletePerson(Person person) throws DataAccessException {
        personRepository.delete(person);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Person> findPersonByLastName(String lastName) throws DataAccessException {
        return personRepository.findByLastName(lastName);
    }
}
