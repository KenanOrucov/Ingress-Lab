package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.service.abstraction.CacheService;
import com.example.Ingress_lab.util.CacheUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheServiceHandler<T> implements CacheService<T> {
    private final CacheUtil cacheUtil;

    @Override
    public void save(String cacheKey, T data) {
        log.info("ActionLog.saveToCache.start {}", data);
        cacheUtil.saveToCache(cacheKey, data, 10L, ChronoUnit.MINUTES);
        log.info("ActionLog.saveToCache.success");
    }

    @Override
    public T get(String cacheKey) {
        T data = cacheUtil.getBucket(cacheKey);
        log.info("ActionLog.cacheData: {}", data);
        return data;
    }
}
