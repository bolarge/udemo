package com.arc.udemo.service;

import com.arc.udemo.domain.billing.Bill;
import com.arc.udemo.domain.billing.Fee;
import com.arc.udemo.domain.billing.Band;
import com.arc.udemo.domain.users.Role;
import com.arc.udemo.domain.users.User;
import com.arc.udemo.repository.UserRepository;
import com.arc.udemo.rest.dto.*;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface UDemoService {

    Optional<User> findUserById(Integer id) throws DataAccessException;
    Collection<User> findAllUser() throws DataAccessException;
    Page<User> findAll(Pageable pageable) throws DataAccessException;
    User saveUser(UserCreationRequest user) throws DataAccessException;
    User findUserByEmail(String email) throws DataAccessException;
    User subscribeUserToPlan(UsageSubscriptionRequest subscriptionRequestDTO);
    UserRepository getUserRepository();
    //
    Role createRole(RoleCreationRequest roleCreationRequest);
    //
    Fee saveFee(Fee fee) throws DataAccessException;
    //
    Band saveUsagePlan(UsagePlanRequest usagePlanRequest) throws DataAccessException;
    //
    Bill generateUserMonthlyBill(MonthlyBillRequest monthlyBillRequest) throws Exception;
    //Collection<Bill> findAllBill(MonthlyBillRequest monthlyBillRequest) throws DataAccessException
    Collection<Bill> generateMonthlyBill(MonthlyBillRequest monthlyBillRequest) throws Exception;

}
