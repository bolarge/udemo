package com.arc.udemo.service;

import com.arc.udemo.domain.billing.Fee;
import com.arc.udemo.domain.billing.UsagePlan;
import com.arc.udemo.domain.users.User;
import com.arc.udemo.rest.dto.UsagePlanDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface UDemoService {

    Optional<User> findUserById(Integer id) throws DataAccessException;
    Collection<User> findAllUser() throws DataAccessException;
    Page<User> findAll(Pageable pageable) throws DataAccessException;
    User saveUser(User user) throws DataAccessException;
    User findUserByEmail(String email) throws DataAccessException;

    Fee saveFee(Fee fee) throws DataAccessException;
    //Bill saveBill(Bill bill) throws DataAccessException;
    UsagePlan saveUsagePlan(UsagePlanDTO usagePlanDTO) throws DataAccessException;

}
