package com.example.testappl.Spring;

import org.threeten.bp.LocalDateTime;


public class SpringUser {

    private Long id; //сортировка по id на сервере
    private Object user;
    private String text;
    private String title;
    private String tags;
    private Boolean published;
    private LocalDateTime lastUpdated;

    public SpringUser(String text, String title, String tags, Boolean published, LocalDateTime lastUpdated) {
        this.text = text;
        this.title = title;
        this.tags = tags;
        this.published = published;
        this.lastUpdated = lastUpdated;
    }

    public SpringUser() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
