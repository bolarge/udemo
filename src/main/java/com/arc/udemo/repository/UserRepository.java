package com.arc.udemo.repository;

import com.arc.udemo.domain.User;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface UserRepository  {

	Collection<User> findByLastName(String lastName) throws DataAccessException;

	User findById(long id) throws DataAccessException;


	void save(User user) throws DataAccessException;


	Collection<User> findAll() throws DataAccessException;


	void delete(User person) throws DataAccessException;


}
