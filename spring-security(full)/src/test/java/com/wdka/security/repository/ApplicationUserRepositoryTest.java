package com.wdka.security.repository;

import com.wdka.security.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by alireza on 4/23/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationUserRepositoryTest {

    @Autowired
    private UserRepository applicationUserRepository;


    @Test
    public void test(){
        User alireza = applicationUserRepository.findByUsername("lynda");
        System.out.println(alireza.getPassword());
    }

}