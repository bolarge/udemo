package com.arc.udemo.service;

import com.arc.udemo.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface UDemoService {

    Optional<User> findUserById(int id) throws DataAccessException;
    Collection<User> findAllUser() throws DataAccessException;
    Page<User> findAll(Pageable pageable) throws DataAccessException;
    User saveUser(User user) throws DataAccessException;
    void deleteUser(Integer userId) throws DataAccessException;
    Collection<User> findUserByLastName(String lastName) throws DataAccessException;
    User findUserByEmail(String email) throws DataAccessException;


}
