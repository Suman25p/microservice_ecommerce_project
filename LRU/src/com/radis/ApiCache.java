package com.radis;

import java.util.concurrent.ConcurrentHashMap;

public class ApiCache<K, V> {

    private final ConcurrentHashMap<K, CacheEntry<V>> cache =
            new ConcurrentHashMap<>();

    private final long ttlMillis;

    public ApiCache(long ttlMillis) {
        this.ttlMillis = ttlMillis;
    }

    public V get(K key) {

        CacheEntry<V> entry = cache.get(key);

        if (entry == null) {
            return null;
        }

        if (entry.isExpired()) {
            cache.remove(key);
            return null;
        }

        return entry.getData();
    }

    public void put(K key, V value) {
        cache.put(key, new CacheEntry<>(value, ttlMillis));
    }

    public void remove(K key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }

    public int size() {
        return cache.size();
    }
}
