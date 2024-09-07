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
public class Logger {

    @Pointcut(value = "execution(* com.example.Ingress_lab.service.concrete.*.*(..))")
    public void LogPointCut() {

    }

    @SneakyThrows
    @Around(value = "LogPointCut()")
    public Object log(ProceedingJoinPoint jp){
        log.info("ActionLog.{}.start fields: {}", jp.getSignature().getName(), jp.getArgs());
        var response = jp.proceed();
        log.info("ActionLog.{}.end fields: {}", jp.getSignature().getName(), jp.getArgs());
        return response;
    }
}
