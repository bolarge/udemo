package com.arc.udemo.repository.base;

import com.arc.udemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface UserBaseRepository<T> extends JpaRepository<T, Long> {

	T findByEmail(String email);

	Optional<T> findById(Long userId);

}
