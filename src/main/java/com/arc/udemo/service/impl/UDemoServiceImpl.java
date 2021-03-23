package com.arc.udemo.service.impl;

import com.arc.udemo.domain.User;
import com.arc.udemo.repository.UserRepository;
import com.arc.udemo.service.UDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

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
    public Optional<User> findUserById(int id) throws DataAccessException {
        Optional<User> person = null;
        try {
            person = userRepository.findById(id);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e) {
            return null;
        }
        return person;
    }

    @Override
    public Collection<User> findAllUser() throws DataAccessException {
        return null;
    }

    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) throws DataAccessException {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public User saveUser(User person) throws DataAccessException {
        return userRepository.save(person);
    }

    @Override
    @Transactional
    public void deleteUser(Integer userId) throws DataAccessException {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<User> findUserByLastName(String lastName) throws DataAccessException {
        return userRepository.findByLastName(lastName);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) throws DataAccessException {
        return userRepository.findUserByEmail(email);
    }
}
