package com.wdka.security.repository;

import com.wdka.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alireza on 4/23/20.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Long findLastId();

    @Query(value = "insert into user_role (user_id, role_id) VALUES (:id , 3)", nativeQuery = true)
    void insertIntoUserRole(@Param("id") Long id);


}
