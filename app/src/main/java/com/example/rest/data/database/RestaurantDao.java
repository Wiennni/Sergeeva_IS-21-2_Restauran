package com.example.rest.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.rest.data.model.RestaurantModel;
import com.example.rest.presentation.mainpage.MainPageFragment;

import java.util.List;

@Dao
public interface RestaurantDao {
    @Query("SELECT * FROM restaurants")
    List<RestaurantModel> getRestaurantAll();
    @Query("SELECT * FROM restaurants WHERE id LIKE :restaurantId")
    RestaurantModel getRestaurantById(int restaurantId);
    @Insert
    void insertAll(RestaurantModel... restaurants);
    @Insert
    void insertAllAsync (RestaurantModel... restaurants);
    @Delete
    void delete(RestaurantModel restaurant);
    @Query("DELETE FROM restaurants")
    void deleteAll();
}
