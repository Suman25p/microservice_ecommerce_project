package com.radis;

import java.util.HashMap;

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache2 {

    private final int capacity;
    private final HashMap<Integer, Node> map;
    private final Node head;
    private final Node tail;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();

        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insert(Node node) {
        Node prev = tail.prev;

        prev.next = node;
        node.prev = prev;

        node.next = tail;
        tail.prev = node;
    }

    public int get(int key) {

        if (!map.containsKey(key))
            return -1;

        Node node = map.get(key);

        remove(node);
        insert(node);

        return node.value;
    }

    public void put(int key, int value) {

        if (map.containsKey(key)) {

            Node node = map.get(key);
            node.value = value;

            remove(node);
            insert(node);

            return;
        }

        if (map.size() == capacity) {

            Node lru = head.next;

            remove(lru);

            map.remove(lru.key);
        }

        Node node = new Node(key, value);

        insert(node);

        map.put(key, node);
    }

    public void display() {

        Node current = head.next;

        while (current != tail) {

            System.out.print("(" + current.key + "," + current.value + ") ");

            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2);

        cache.put(1, 10);
        cache.put(2, 20);

        cache.display();

        System.out.println(cache.get(1));

        cache.display();

        cache.put(3, 30);

        cache.display();

        System.out.println(cache.get(2));

        cache.put(4, 40);

        cache.display();

        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}