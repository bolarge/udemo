package com.arc.udemo.repository;

import com.arc.udemo.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@Profile("spring-data-jpa")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	Collection<User> findByLastName(String lastName);
}
