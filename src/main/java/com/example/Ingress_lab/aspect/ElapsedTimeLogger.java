package com.example.Ingress_lab.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ElapsedTimeLogger {

    @Pointcut(value = "execution(* com.example.Ingress_lab.service.concrete.*.*(..))")
    public void elapsedTimePointCut() {

    }

    @SneakyThrows
    @Around(value = "elapsedTimePointCut()")
    public Object logElapsedTime(ProceedingJoinPoint jp){
        var startTime = System.currentTimeMillis();
        var response = jp.proceed();
        var endTime = System.currentTimeMillis();
        log.info("Elapsed time: {} ms", endTime - startTime);
        return response;
    }
}
