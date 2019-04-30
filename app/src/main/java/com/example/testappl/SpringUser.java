package com.example.testappl;

public class SpringUser {
    public SpringUser(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public SpringUser() {
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    String name;
    String id;
}
