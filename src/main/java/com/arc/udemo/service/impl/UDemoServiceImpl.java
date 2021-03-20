package com.arc.udemo.service.impl;

import com.arc.udemo.domain.User;
import com.arc.udemo.repository.UserRepository;
import com.arc.udemo.service.UDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UDemoServiceImpl implements UDemoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UDemoServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(long id) throws DataAccessException {
        User person = null;
        try {
            person = userRepository.findById(id);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e) {
            return null;
        }
        return person;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<User> findAllUser() throws DataAccessException {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User person) throws DataAccessException {
        userRepository.save(person);
    }

    @Override
    @Transactional
    public void deleteUser(User person) throws DataAccessException {
        userRepository.delete(person);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<User> findUserByLastName(String lastName) throws DataAccessException {
        return userRepository.findByLastName(lastName);
    }
}
