package com.example.rest.data.repository;

import android.os.AsyncTask;

import com.example.rest.data.database.GetTableDatabase;
import com.example.rest.data.model.RestaurantModel;
import com.example.rest.domain.Restaurant;

import java.util.ArrayList;
import java.util.List;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.example.rest.presentation.mainpage.MainPageFragment;

public class DatabaseRepository {
    private final GetTableDatabase database;
    private final ExecutorService executorService;

    public DatabaseRepository(GetTableDatabase database) {
        this.database = database;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void getRestaurantByIdAsync(int id, Consumer<Restaurant> callback) {
        executorService.execute(() -> {
            Restaurant restaurant = database.restaurantDao().getRestaurantById(id).toDomainModel();
            callback.accept(restaurant);
        });
    }

    public void getAllRestaurantsAsync(Consumer<List<Restaurant>> callback) {
        executorService.execute(() -> {
            List<RestaurantModel> restaurantModels = database.restaurantDao().getRestaurantAll();
            List<Restaurant> restaurants = new ArrayList<>();
            for (RestaurantModel model : restaurantModels) {
                restaurants.add(model.toDomainModel());
            }
            callback.accept(restaurants);
        });
    }

    public void deleteAll() {
        executorService.execute(() -> {
            database.restaurantDao().deleteAll();
        });
    }

    public void insertRestaurantsAsync(RestaurantModel... restaurantModels) {
        executorService.execute(() -> {
            database.restaurantDao().insertAll(restaurantModels);
        });
    }

    public interface Consumer<T> {
        void accept(T t);
    }


    private static class GetRestaurantAsyncTask extends AsyncTask<Void, Void, Restaurant> {
        private final GetTableDatabase database;
        private final int id;
        private final Consumer<Restaurant> callback;
        public GetRestaurantAsyncTask(GetTableDatabase database, int id, Consumer<Restaurant> callback) {
            this.database = database;
            this.id = id;
            this.callback = callback;
        }
        @Override
        protected Restaurant doInBackground (Void... voids) {
            return database.restaurantDao().getRestaurantById(id).toDomainModel();
        }
        @Override
        protected void onPostExecute (Restaurant restaurant) { callback.accept(restaurant); }
    }

    private static class GetAllRestaurantsAsyncTask extends AsyncTask<Void, Void, List<Restaurant>> {
        private final GetTableDatabase database;
        private final Consumer<List<Restaurant>> callback;
        public GetAllRestaurantsAsyncTask(GetTableDatabase database, Consumer<List<Restaurant>> callback) {
            this.database = database;
            this.callback = callback;
        }
        @Override
        protected List<Restaurant> doInBackground (Void... voids) {
            List<RestaurantModel> restaurantModels = database.restaurantDao().getRestaurantAll();
            List<Restaurant> restaurants = new ArrayList<>();
            for (RestaurantModel model : restaurantModels) {
                restaurants.add(model.toDomainModel());
            }
            return restaurants;
        }
        @Override
        protected void onPostExecute (List<Restaurant> restaurants) { callback.accept(restaurants); }
    }

    private static class InsertRestaurantsAsyncTask extends AsyncTask<RestaurantModel, Void, Void> {
        private final GetTableDatabase database;
        public InsertRestaurantsAsyncTask(GetTableDatabase database) { this.database = database; }
        @Override
        protected Void doInBackground (RestaurantModel... restaurantModels) {
            database.restaurantDao().insertAll(restaurantModels);
            return null;
        }
    }
}


