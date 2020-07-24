package com.wdka.security.repository;

import com.wdka.security.model.Role;
import com.wdka.security.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceContext;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by alireza on 4/26/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void test() {
        Role admin = roleRepository.findByRoleName("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(admin);
        userDao.saveUser(new User("Nader23332", "fsdklfjiafjiwejjfaiojf", roles));
    }

}