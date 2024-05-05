package com.example.rest.domain;

public class Restaurant {

    public final int id;
    public final String name;
    public final String imageUrl;
    public final String address;
    public final String workingTime;
    public final String description;
    public Restaurant(int id, String name, String imageUrl, String address, String workingTime, String description) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.address = address;
        this.workingTime = workingTime;
        this.description = description;
    }
}
