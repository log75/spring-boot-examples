package com.example.gitapi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by alireza on 7/17/20.
 */
@Aspect
@Component
public class LoggingAspect {

    private Logger logger;

    @Autowired
    public LoggingAspect(Logger logger) {
        this.logger = logger;
    }

    @Pointcut("execution(* com.example.gitapi.controller.*.*(..))")
    public void pc1() {
    }


    @After("pc1()")
    public void loggingAdvise(JoinPoint joinPoint){
        logger.info(joinPoint.getThis().getClass().getName());
    }

}
