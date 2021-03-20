package com.arc.udemo.repository;

import com.arc.udemo.api.IUser;
import com.arc.udemo.domain.User;
import com.arc.udemo.repository.base.UserBaseRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends UserBaseRepository<User> {

	IUser save(IUser iUser);

}
