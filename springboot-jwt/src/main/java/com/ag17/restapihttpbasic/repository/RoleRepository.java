package com.ag17.restapihttpbasic.repository;

import com.ag17.restapihttpbasic.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by alireza on 6/19/20.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
