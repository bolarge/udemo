package com.arc.udemo.service.impl;

import com.arc.udemo.domain.billing.Band;
import com.arc.udemo.domain.billing.Bill;
import com.arc.udemo.domain.billing.BillType;
import com.arc.udemo.domain.billing.Fee;
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
    private BandRepository bandRepository;
    private APIUsageRepository apiUsageRepository;

    @Autowired
    public UDemoServiceImpl(UserRepository userRepository, FeeRepository feeRepository,
                            BandRepository bandRepository, BillRepository billRepository, APIUsageRepository apiUsageRepository){
        this.userRepository = userRepository;
        this.feeRepository = feeRepository;
        this.bandRepository = bandRepository;
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
    public Band saveUsagePlan(UsagePlanRequest usagePlanRequest) throws DataAccessException {
        Optional<Fee> fee = feeRepository.findById(Integer.parseInt(usagePlanRequest.getFee()));
        Band band = new Band(usagePlanRequest.getName(), usagePlanRequest.getDescription(),
                Double.parseDouble(usagePlanRequest.getPrice()), fee.get());
        return bandRepository.save(band);
    }

    @Override
    public User subscribeUserToPlan(UsageSubscriptionRequest subscriptionRequestDTO) {
        Optional<Band> usagePlan = bandRepository.findById(Integer.parseInt(subscriptionRequestDTO.getPlan()));
        User user = userRepository.findUserByEmail(subscriptionRequestDTO.getEmail());
        user.setBand(usagePlan.get());
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
        Band band = new Band();
        if(usage >= 0 && usage <= 1000000){
            band = bandRepository.findUsagePlanByName("Lowest");
        }
        else if(usage > 1000001 && usage <= 10000000){
            band = bandRepository.findUsagePlanByName("Medium");
        }
        else if(usage > 10000001){
            band = bandRepository.findUsagePlanByName("Highest");
        }
        user.setBand(band);
        monthlyBill.setName(BillType.Monthly.toString() + " Bill");
        monthlyBill.setDescription("API Service Monthly Bill");
        monthlyBill.setUser(user);
        monthlyBill.setBand(band);
        monthlyBill.setSubTotal(band.getPrice());
        monthlyBill.setTax((1.5 / 100) * band.getPrice());
        monthlyBill.setTotal(monthlyBill.getSubTotal() + monthlyBill.getTax());
        userRepository.save(user);
        return billRepository.save(monthlyBill);
    }
}
