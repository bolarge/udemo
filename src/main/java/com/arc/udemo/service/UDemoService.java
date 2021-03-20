package com.arc.udemo.service;

import com.arc.udemo.domain.User;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface UDemoService {

    User findUserById(long id) throws DataAccessException;
    Collection<User> findAllUser() throws DataAccessException;
    void saveUser(User user) throws DataAccessException;
    void deleteUser(User user) throws DataAccessException;
    Collection<User> findUserByLastName(String lastName) throws DataAccessException;


}
