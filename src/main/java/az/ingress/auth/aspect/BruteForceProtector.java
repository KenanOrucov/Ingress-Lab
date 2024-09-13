package az.ingress.auth.aspect;

import az.ingress.auth.util.CacheProvider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static az.ingress.auth.exception.ErrorMessage.BRUTE_FORCE_EXCEPTION;
import static az.ingress.auth.model.constants.CacheConstants.CACHE_BRUTE_REFRESH_SECONDS;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class BruteForceProtector {
    private final CacheProvider cacheProvider;

    @SneakyThrows
    @Pointcut("execution(* az.ingress.auth.service.AuthService.signIn(..))")
    public void bruteForce() {

    }

    @SneakyThrows
    @Around(value = "bruteForce()")
    public Object log(ProceedingJoinPoint jp){
        var userId = jp.getArgs()[0];
        String cacheKey = "brute-id:" + userId;
        Integer requestCount = cacheProvider.getBucket(cacheKey);
        log.info("requestCount: " + requestCount);
        if (requestCount == null) {
            requestCount = 0;
        }

        requestCount++;

        if (requestCount >= 5) {
            log.error(BRUTE_FORCE_EXCEPTION.getMessage());
            Thread.sleep(1000 * 60 * 5);
        }

        cacheProvider.updateToCache(requestCount, cacheKey, CACHE_BRUTE_REFRESH_SECONDS);
        return jp.proceed();
    }

}
