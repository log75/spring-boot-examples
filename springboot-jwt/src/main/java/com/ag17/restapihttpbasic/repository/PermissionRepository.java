package com.ag17.restapihttpbasic.repository;

import com.ag17.restapihttpbasic.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by alireza on 6/19/20.
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
