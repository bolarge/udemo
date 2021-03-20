package com.arc.udemo.repository;

import com.arc.udemo.api.IUser;
import org.springframework.dao.DataAccessException;

public interface UserRepository  {

	IUser save(IUser iUser)throws DataAccessException;;

}
