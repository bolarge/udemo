package com.arc.udemo.repository.base;

import com.arc.udemo.domain.User;
import com.arc.udemo.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

@Profile("spring-data-jpa")
public interface SpringDataUserRepository extends PagingAndSortingRepository<User, Long> {

}
