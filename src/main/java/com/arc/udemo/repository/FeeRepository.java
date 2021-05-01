package com.arc.udemo.repository;

import com.arc.udemo.domain.billing.Fee;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("spring-data-jpa")
public interface FeeRepository extends CrudRepository<Fee, Integer> {
}
