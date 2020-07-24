package com.ag17.restapihttpbasic;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class RestApiHttpBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiHttpBasicApplication.class, args);
    }

}
