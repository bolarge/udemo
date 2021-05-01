package com.arc.udemo.service.impl;

import com.arc.udemo.domain.billing.Fee;
import com.arc.udemo.domain.billing.UsagePlan;
import com.arc.udemo.domain.users.User;
import com.arc.udemo.repository.FeeRepository;
import com.arc.udemo.repository.UsagePlanRepository;
import com.arc.udemo.repository.UserRepository;
import com.arc.udemo.rest.dto.UsagePlanDTO;
import com.arc.udemo.service.UDemoService;
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

    private UserRepository userRepository;
    //private BillRepository billRepository;
    private FeeRepository feeRepository;
    private UsagePlanRepository usagePlanRepository;


    @Autowired
    public UDemoServiceImpl(UserRepository userRepository, FeeRepository feeRepository,
                            UsagePlanRepository usagePlanRepository){
        this.userRepository = userRepository;
        this.feeRepository = feeRepository;
        this.usagePlanRepository = usagePlanRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserById(Integer id) throws DataAccessException {
        Optional<User> user;
        try {
            user = userRepository.findById(id);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e) {
            return null;
        }
        return user;
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
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) throws DataAccessException {
        return userRepository.findUserByEmail(email);
    }

    @Override
    @Transactional
    public Fee saveFee(Fee fee) throws DataAccessException {
        return feeRepository.save(fee);
    }

 /*   @Override
    @Transactional
    public Bill saveBill(Bill bill) throws DataAccessException {
        return billRepository.save(bill);
    }*/

    @Override
    @Transactional
    public UsagePlan saveUsagePlan(UsagePlanDTO usagePlanDTO) throws DataAccessException {
        Optional<Fee> fee = feeRepository.findById(Integer.parseInt(usagePlanDTO.getFee()));
        UsagePlan usagePlan = new UsagePlan(usagePlanDTO.getName(), usagePlanDTO.getDescription(),
                Double.parseDouble(usagePlanDTO.getPrice()), fee.get());

        return usagePlanRepository.save(usagePlan);
    }
}
