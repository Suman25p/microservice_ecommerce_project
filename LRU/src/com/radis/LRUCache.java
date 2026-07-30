package com.radis;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

	private final int capacity;
	private final LinkedHashMap<Integer, Integer> cache;

	public LRUCache(int capacity) {
		this.capacity = capacity;

		cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {

			@Override
			protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
				return size() > LRUCache.this.capacity;
			}
		};
	}

	public int get(int key) {
		return cache.getOrDefault(key, -1);
	}

	public void put(int key, int value) {
		cache.put(key, value);
	}

	public void display() {
		System.out.println(cache);
	}

	public static void main(String[] args) {

		LRUCache lru = new LRUCache(2);

		lru.put(1, 10);
		lru.put(2, 20);
		lru.display();

		System.out.println(lru.get(1));

		lru.put(3, 30);
		lru.display();

		System.out.println(lru.get(2));

		lru.put(4, 40);
		lru.display();

		System.out.println(lru.get(1));
		System.out.println(lru.get(3));
		System.out.println(lru.get(4));
	}
}
