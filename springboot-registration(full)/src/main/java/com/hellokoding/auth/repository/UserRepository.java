package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Modifying
    @Query(value = "insert into user_roles (users_id, roles_id) VALUES (:id , 1)", nativeQuery = true)
    void insertIntoUserRole(@Param("id") Long id);

}
