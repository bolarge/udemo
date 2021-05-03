package com.arc.udemo.repository;

import com.arc.udemo.domain.billing.Band;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("spring-data-jpa")
public interface BandRepository extends JpaRepository<Band, Integer> {
     Band findUsagePlanByName(String name);
}
