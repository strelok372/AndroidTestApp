package com.example.testappl.Spring;

public class SpringUser {

    String tags;
    String name;
    Integer id;
    String text;

    public SpringUser(String tags, String name, Integer id, String text) {
        this.tags = tags;
        this.name = name;
        this.id = id;
        this.text = text;
    }

    public SpringUser(String text, Integer id) {
        this.text = text;
        this.id = id;
    }

    public SpringUser() {
    }

    public String getName() {
        return name;
    }
    public Integer getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public String getTags() {
        return tags;
    }


}
