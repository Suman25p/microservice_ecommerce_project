package com.radis;

public class CacheEntry<T> {
	private T data;
    private long expiryTime;

    public CacheEntry(T data, long ttlMillis) {
        this.data = data;
        this.expiryTime = System.currentTimeMillis() + ttlMillis;
    }

    public T getData() {
        return data;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}
