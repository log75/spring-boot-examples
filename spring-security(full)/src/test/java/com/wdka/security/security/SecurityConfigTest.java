package com.wdka.security.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * Created by alireza on 4/21/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityConfigTest {



    @Test
    public void test() {
       // Set<GrantedAuthority> permissions = UserRole.ADMIN.getGrantedAuthority();
        //permissions.forEach(System.out::println);
    }
}

enum Size {
    MEDIUM("m"), LEARG("l"), SMALL("s");

    private String sName;

    Size(String sName) {
        this.sName = sName;
    }

    public String getsName() {
        return sName;
    }
}