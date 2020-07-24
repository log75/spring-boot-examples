package com.wdka.security.repository;

import com.wdka.security.model.Role;
import com.wdka.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by alireza on 4/26/20.
 */
@Repository
public class UserDao {

    @PersistenceContext
    EntityManager entityManager;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDao(RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public boolean isUserExist(String userName) {

        List uname = entityManager.createQuery("select c from User c where c.username = :uname").setParameter("uname", userName).getResultList();
        return uname.isEmpty() ? false : true;

    }

    @Transactional
    public void saveUser(User user) {
        user.setEnable(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleName("ADMINTRAINEE"));
        user.setRoles(roles);
        entityManager.persist(user);
        entityManager.createNativeQuery("INSERT INTO user_role (user_id, role_id) VALUES (?1, 2)").setParameter(1, user.getId()).executeUpdate();
    }

}
