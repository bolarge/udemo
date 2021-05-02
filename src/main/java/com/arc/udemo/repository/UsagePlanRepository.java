package com.arc.udemo.repository;

import com.arc.udemo.domain.billing.UsagePlan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("spring-data-jpa")
public interface UsagePlanRepository extends JpaRepository<UsagePlan, Integer> {
     UsagePlan findUsagePlanByName(String name);
}
