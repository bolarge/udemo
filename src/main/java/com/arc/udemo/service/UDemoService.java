package com.arc.udemo.service;

import com.arc.udemo.domain.Person;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface UDemoService {

    Person findPersonById(int id) throws DataAccessException;
    Collection<Person> findAllPerson() throws DataAccessException;
    void savePerson(Person person) throws DataAccessException;
    void deletePerson(Person person) throws DataAccessException;
    Collection<Person> findPersonByLastName(String lastName) throws DataAccessException;

}
