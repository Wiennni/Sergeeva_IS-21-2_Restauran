package com.example.rest.data.database;
import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rest.data.model.RestaurantModel;
import com.example.rest.presentation.mainpage.MainPageFragment;

@Database(entities = {RestaurantModel.class}, version = 1)
public abstract class GetTableDatabase extends RoomDatabase {
    public abstract RestaurantDao restaurantDao();
    private static volatile GetTableDatabase instance;
    private static final Object LOCK = new Object();

    public static GetTableDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            GetTableDatabase.class, "get-table-database").build();
                    Log.d("GetTableDatabase", "Database instance created successfully");
                }
            }
        }
        return instance;
    }
}