package com.ag17.restapihttpbasic.repository;

import com.ag17.restapihttpbasic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by alireza on 6/19/20.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsById(Long id);
}
