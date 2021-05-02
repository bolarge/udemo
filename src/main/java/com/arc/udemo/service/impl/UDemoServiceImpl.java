package com.arc.udemo.service.impl;

import com.arc.udemo.domain.billing.Bill;
import com.arc.udemo.domain.billing.BillType;
import com.arc.udemo.domain.billing.Fee;
import com.arc.udemo.domain.billing.UsagePlan;
import com.arc.udemo.domain.users.User;
import com.arc.udemo.repository.*;
import com.arc.udemo.rest.dto.MonthlyBillRequest;
import com.arc.udemo.rest.dto.UsagePlanRequest;
import com.arc.udemo.rest.dto.UsageSubscriptionRequest;
import com.arc.udemo.service.UDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

@Service
public class UDemoServiceImpl implements UDemoService {

    private UserRepository userRepository;
    private BillRepository billRepository;
    private FeeRepository feeRepository;
    private UsagePlanRepository usagePlanRepository;
    private APIUsageRepository apiUsageRepository;

    @Autowired
    public UDemoServiceImpl(UserRepository userRepository, FeeRepository feeRepository,
                            UsagePlanRepository usagePlanRepository, BillRepository billRepository, APIUsageRepository apiUsageRepository){
        this.userRepository = userRepository;
        this.feeRepository = feeRepository;
        this.usagePlanRepository = usagePlanRepository;
        this.billRepository = billRepository;
        this.apiUsageRepository = apiUsageRepository;
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

    @Override
    @Transactional
    public UsagePlan saveUsagePlan(UsagePlanRequest usagePlanRequest) throws DataAccessException {
        Optional<Fee> fee = feeRepository.findById(Integer.parseInt(usagePlanRequest.getFee()));
        UsagePlan usagePlan = new UsagePlan(usagePlanRequest.getName(), usagePlanRequest.getDescription(),
                Double.parseDouble(usagePlanRequest.getPrice()), fee.get());
        return usagePlanRepository.save(usagePlan);
    }

    @Override
    public User subscribeUserToPlan(UsageSubscriptionRequest subscriptionRequestDTO) {
        Optional<UsagePlan> usagePlan = usagePlanRepository.findById(Integer.parseInt(subscriptionRequestDTO.getPlan()));
        User user = userRepository.findUserByEmail(subscriptionRequestDTO.getEmail());
        user.setUsagePlan(usagePlan.get());
        return  userRepository.save(user);
    }

    @Override
    @Transactional
    public Bill generateUserMonthlyBill(MonthlyBillRequest monthlyBillRequest) throws DataAccessException {
        Bill monthlyBill = new Bill();
        //Query Usage table for total counts of user APICall consumption within given month
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(monthlyBillRequest.getStartDate());
        LocalDate endDate = LocalDate.parse(monthlyBillRequest.getEndDate());
        User user = userRepository.findUserByEmail(monthlyBillRequest.getEmail());

        //Calculate usage
        Long usage = apiUsageRepository.countAPICallEventByUserEmailAndRequestDateIsBetween(user.getEmail(), startDate, endDate);

        //Get User UsagePlan
        UsagePlan usagePlan = new UsagePlan();
        if(usage >= 0 && usage <= 1000000){
            usagePlan = usagePlanRepository.findUsagePlanByName("Lowest");
        }
        else if(usage > 1000001 && usage <= 10000000){
            usagePlan = usagePlanRepository.findUsagePlanByName("Medium");
        }
        else if(usage > 10000001){
            usagePlan = usagePlanRepository.findUsagePlanByName("Highest");
        }
        user.setUsagePlan(usagePlan);
        monthlyBill.setName(BillType.Monthly.toString() + " Bill");
        monthlyBill.setDescription("API Service Monthly Bill");
        monthlyBill.setUser(user);
        monthlyBill.setUsagePlan(usagePlan);
        monthlyBill.setSubTotal(usagePlan.getPrice());
        monthlyBill.setTax((1.5 / 100) * usagePlan.getPrice());
        monthlyBill.setTotal(monthlyBill.getSubTotal() + monthlyBill.getTax());
        userRepository.save(user);
        return billRepository.save(monthlyBill);
    }
}
