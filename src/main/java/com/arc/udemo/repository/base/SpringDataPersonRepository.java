package com.arc.udemo.repository.base;

import com.arc.udemo.domain.Person;
import com.arc.udemo.repository.PersonRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;

@Profile("spring-data-jpa")
public interface SpringDataPersonRepository extends PersonRepository, Repository<Person, Long> {

}
