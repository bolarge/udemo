package com.arc.udemo.service.impl;

import com.arc.udemo.domain.billing.Band;
import com.arc.udemo.domain.billing.Bill;
import com.arc.udemo.domain.billing.BillType;
import com.arc.udemo.domain.billing.Fee;
import com.arc.udemo.domain.users.Role;
import com.arc.udemo.domain.users.Title;
import com.arc.udemo.domain.users.User;
import com.arc.udemo.repository.*;
import com.arc.udemo.rest.dto.*;
import com.arc.udemo.service.EmailService;
import com.arc.udemo.service.EventService;
import com.arc.udemo.service.UDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class UDemoServiceImpl implements UDemoService {
    private static Logger logger = LoggerFactory.getLogger(UDemoServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EventService eventService;

    private UserRepository userRepository;
    private BillRepository billRepository;
    private FeeRepository feeRepository;
    private BandRepository bandRepository;
    private APIUsageRepository apiUsageRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UDemoServiceImpl(UserRepository userRepository, FeeRepository feeRepository,
                            BandRepository bandRepository, BillRepository billRepository, APIUsageRepository apiUsageRepository,
                            RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.feeRepository = feeRepository;
        this.bandRepository = bandRepository;
        this.billRepository = billRepository;
        this.apiUsageRepository = apiUsageRepository;
        this.roleRepository = roleRepository;
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
    public User saveUser(UserCreationRequest userCreationRequest) throws DataAccessException {
        User user = new User();
        user.setFirstName(userCreationRequest.getFirstName());
        user.setLastName(userCreationRequest.getLastName());
        user.setTitle(Title.valueOf(userCreationRequest.getTitle()));
        user.setMobilePhone(userCreationRequest.getMobileNumber());
        user.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
        user.setAdmin(userCreationRequest.isAdmin());
        user.setEnabled(userCreationRequest.isEnabled());
        user.setEmail(userCreationRequest.getEmail());
        Role role1 = roleRepository.findRoleByName(userCreationRequest.getRole());
        user.getRoles().add(role1);
        return userRepository.save(user);
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
        userRepository.save(user); //Customer's current bill
        return billRepository.save(monthlyBill);
    }

    @Override
    @Transactional
    public Collection<Bill> generateMonthlyBill(MonthlyBillRequest monthlyBillRequest) throws DataAccessException {
        //
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(monthlyBillRequest.getStartDate());
        LocalDate endDate = LocalDate.parse(monthlyBillRequest.getEndDate());
        Band lowestBand = bandRepository.findUsagePlanByName("Lowest");
        Band mediumBand = bandRepository.findUsagePlanByName("Medium");
        Band highestBand = bandRepository.findUsagePlanByName("Highest");
        //
        Collection<User> cusIter = (Collection<User>) userRepository.findAll();
        System.out.println("Customers are: " + cusIter);
        Collection<Bill> customersBills = new ArrayList<>();
        for (User customerMetered: cusIter){
            if(!customerMetered.isAdmin()){
                Bill monthlyBill = new Bill();
                long usage = apiUsageRepository.countAPICallEventByUserEmailAndRequestDateIsBetween(customerMetered.getEmail(), startDate, endDate);
                monthlyBill.setName(BillType.Monthly.toString() + " Bill");
                monthlyBill.setDescription("API Service Monthly Bill");

                if(usage >= 0 && usage <= 1000000){
                    customerMetered.setBand(lowestBand);
                    monthlyBill.setBand(lowestBand);
                    monthlyBill.setSubTotal(lowestBand.getPrice());
                    monthlyBill.setTax((1.5 / 100) * lowestBand.getPrice());
                }
                else if(usage > 1000001 && usage <= 10000000){
                    customerMetered.setBand(mediumBand);
                    monthlyBill.setBand(mediumBand);
                    monthlyBill.setSubTotal(mediumBand.getPrice());
                    monthlyBill.setTax((1.5 / 100) * mediumBand.getPrice());
                }
                else if(usage > 10000001){
                    customerMetered.setBand(highestBand);
                    monthlyBill.setBand(highestBand);
                    monthlyBill.setSubTotal(highestBand.getPrice());
                    monthlyBill.setTax((1.5 / 100) * highestBand.getPrice());
                }
                monthlyBill.setUser(customerMetered);
                monthlyBill.setTotal(monthlyBill.getSubTotal() + monthlyBill.getTax());
                customersBills.add(monthlyBill);
                billRepository.save(monthlyBill);
                userRepository.save(customerMetered);
            }
        }
        return  customersBills;
    }

    private String getClientIP() {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0];
        }
        return request.getRemoteAddr();
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    @Transactional
    public Role createRole(RoleCreationRequest roleCreationRequest) {
        Role role = new Role(roleCreationRequest.getName());
        return roleRepository.save(role);
    }
}
