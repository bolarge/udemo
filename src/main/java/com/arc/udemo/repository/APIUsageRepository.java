package com.arc.udemo.repository;

import com.arc.udemo.domain.events.APIEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

@Profile("spring-data-jpa")
public interface APIUsageRepository extends JpaRepository<APIEvent, Integer> {

    Long countAPICallEventByUserEmailAndRequestDateIsBetween(String email, LocalDate startDate, LocalDate endDate);
}
