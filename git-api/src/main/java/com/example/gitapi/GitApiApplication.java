package com.example.gitapi;

import org.aspectj.lang.annotation.After;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;

@SpringBootApplication
public class GitApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitApiApplication.class, args);
    }

}
