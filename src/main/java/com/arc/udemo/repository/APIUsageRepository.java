package com.arc.udemo.repository;

import com.arc.udemo.domain.products.APIUsage;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

@Profile("spring-data-jpa")
public interface APIUsageRepository extends JpaRepository<APIUsage, Integer> {

    Long countAPICallEventByUserEmailAndRequestDateIsBetween(String email, LocalDate startDate, LocalDate endDate);
    //Long countAPICallEventByUserEmailAndRequestDateIsBetween(String email, LocalDate startDate, LocalDate endDate);
}
