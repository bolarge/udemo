package com.arc.udemo.repository;

import com.arc.udemo.domain.billing.Bill;
import com.arc.udemo.domain.billing.BillStatus;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@Profile("spring-data-jpa")
public interface BillRepository extends PagingAndSortingRepository<Bill, Integer> {
    Collection<Bill> findAllByBillStatus(BillStatus billStatus);
}
