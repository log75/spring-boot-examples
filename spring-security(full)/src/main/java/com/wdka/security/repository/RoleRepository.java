package com.wdka.security.repository;

import com.wdka.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by alireza on 4/26/20.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String name);

}
