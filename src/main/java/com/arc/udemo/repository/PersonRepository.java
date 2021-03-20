package com.arc.udemo.repository;

import com.arc.udemo.domain.Person;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface PersonRepository {

    Collection<Person> findByLastName(String lastName) throws DataAccessException;

    Person findById(long id) throws DataAccessException;


    void save(Person owner) throws DataAccessException;


    Collection<Person> findAll() throws DataAccessException;


    void delete(Person owner) throws DataAccessException;
}
