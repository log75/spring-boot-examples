package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by alireza on 5/4/20.
 */
@SpringBootTest
class UserDaoTest {

    @Test
    void testFindByUsername() {
    /*    UserRepository userRepositoryMock = mock(UserRepository.class);
        when(userRepositoryMock.findByUsername("Alireza").getUsername()).thenReturn("Kire khar");
        System.out.println(userRepositoryMock.findByUsername("Alireza").getUsername());*/
        System.out.println("haha");
    }

}