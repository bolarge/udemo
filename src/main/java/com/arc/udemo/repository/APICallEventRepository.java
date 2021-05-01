package com.arc.udemo.repository;

import com.arc.udemo.domain.events.APICallEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("spring-data-jpa")
public interface APICallEventRepository extends CrudRepository<APICallEvent, Integer> {
}
