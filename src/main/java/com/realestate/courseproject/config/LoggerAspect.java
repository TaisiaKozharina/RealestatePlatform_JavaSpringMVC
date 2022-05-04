package com.realestate.courseproject.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Configuration
@Aspect
public class LoggerAspect {


    //Aspect will cover every method and log execution time
    @Around("execution(* com.realestate.courseproject..*.*(..))") //will cover all methods inside main package
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(joinPoint.getSignature().toString() + " method execution start");
        Instant start = Instant.now();
        //  ^ Before
        Object returnObj = joinPoint.proceed();
        //  v After
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        log.info("Time took to execute " + joinPoint.getSignature().toString() + " method: "+timeElapsed);
        log.info(joinPoint.getSignature().toString() + " method execution end");
        return returnObj;
    }

    //Aspect will react to any exception inside application and log details
    @AfterThrowing(value = "execution(* com.realestate.courseproject.*.*(..))",throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }
}
