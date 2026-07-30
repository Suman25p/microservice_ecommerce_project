package com.radis;

public class Main {

    public static void main(String[] args) {

        ApiService apiService = new ApiService();

        ApiCache<String, String> cache =
                new ApiCache<>(5000); // TTL = 5 seconds

        String userId = "101";

        // First Request
        String response = cache.get(userId);

        if (response == null) {
            response = apiService.fetchUser(userId);
            cache.put(userId, response);
        }

        System.out.println(response);

        // Second Request (Cache Hit)

        response = cache.get(userId);

        if (response == null) {
            response = apiService.fetchUser(userId);
            cache.put(userId, response);
        }

        System.out.println(response);

        // Wait for cache expiry

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nCache Expired\n");

        response = cache.get(userId);

        if (response == null) {
            response = apiService.fetchUser(userId);
            cache.put(userId, response);
        }

        System.out.println(response);
    }
}