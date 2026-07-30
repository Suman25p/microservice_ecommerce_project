package com.radis;

public class ApiService {
	public String fetchUser(String userId) {

        System.out.println("Calling External API...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "User Details for " + userId;
    }
}
