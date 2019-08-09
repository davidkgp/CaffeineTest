package com.test.cache.util;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.AllArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class MyCache<K, V> {


    private final Cache<K, V> cache;
    private final AsyncCache<K, V> asyncCache;


    public MyCache(long expiryAfterWriteTime, TimeUnit expiryAfterWriteTimeUnit){
        cache = Caffeine.newBuilder()
                .maximumSize(20)
                .expireAfterWrite(expiryAfterWriteTime, expiryAfterWriteTimeUnit)
                .build();


        asyncCache = Caffeine.newBuilder()
                .maximumSize(20)
                .expireAfterWrite(expiryAfterWriteTime, expiryAfterWriteTimeUnit)
                .buildAsync();
    }







    public V getValue(K cachekey, Function<K, V> fetchValue) {

        System.out.println("Cache ready "+cache);

        cache.

        return cache.get(cachekey, fetchValue::apply);


    }

    public CompletableFuture<V> getValueAsynch(K cachekey, Function<K, V> fetchValue) {

        System.out.println("Async Cache ready "+asyncCache);


        return asyncCache.get(cachekey, fetchValue::apply);

    }


}
