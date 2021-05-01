package com.arc.udemo.repository;

import com.arc.udemo.domain.billing.UsagePlan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("spring-data-jpa")
public interface UsagePlanRepository extends CrudRepository<UsagePlan, Integer> {
}
