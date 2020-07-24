package com.wdka.security.repository;

import com.wdka.security.model.PersistentLogin;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by alireza on 4/24/20.
 */
public interface PersistentRepository extends JpaRepository<PersistentLogin, String> {

    @Modifying
    void deleteByUsername(String userName);

    @Modifying
    void deleteBySeries(String series);


}
