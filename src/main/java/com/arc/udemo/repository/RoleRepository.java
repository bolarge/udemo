package com.arc.udemo.repository;

import com.arc.udemo.domain.users.Role;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("spring-data-jpa")
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findRoleByName(String name);
}
