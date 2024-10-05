package com.example.Ingress_lab.service.abstraction;

public interface CacheService<T> {
    public <T> void save(T data, String cacheKey);
    T get(String cacheKey);
}
