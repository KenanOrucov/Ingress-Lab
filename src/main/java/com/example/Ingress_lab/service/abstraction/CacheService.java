package com.example.Ingress_lab.service.abstraction;

public interface CacheService<T> {
    void save(String cacheKey, T data);
    T get(String cacheKey);
}
