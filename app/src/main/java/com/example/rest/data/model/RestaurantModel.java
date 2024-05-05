package com.example.rest.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.rest.domain.Restaurant;
import com.example.rest.presentation.mainpage.MainPageFragment;

@Entity(tableName = "restaurants")
public class RestaurantModel {
    @PrimaryKey
    public final int id;
    @ColumnInfo(name = "name")
    public final String name;
    @ColumnInfo(name = "image_url")
    public final String imageUrl;
    @ColumnInfo(name = "address")
    public final String address;
    @ColumnInfo(name = "working_time")
    public final String workingTime;
    @ColumnInfo(name = "description")
    public final String description;
    public RestaurantModel(int id, String name, String imageUrl, String address, String workingTime, String description) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.address = address;
        this.workingTime = workingTime;
        this.description = description;
    }
    public Restaurant toDomainModel() {
        return new Restaurant(id, name, imageUrl, address, workingTime, description);
    }
}
