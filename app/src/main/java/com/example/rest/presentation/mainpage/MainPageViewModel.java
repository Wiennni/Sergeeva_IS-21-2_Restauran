package com.example.rest.presentation.mainpage;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rest.App;
import com.example.rest.data.repository.DatabaseRepository;
import com.example.rest.domain.Restaurant;
import com.example.rest.presentation.mainpage.MainPageFragment;

import java.util.List;

public class MainPageViewModel extends ViewModel {
    private final DatabaseRepository databaseRepository = App.getDatabaseRepository();
    private final MutableLiveData<List<Restaurant>> restaurantsData = new MutableLiveData<>();

    public MutableLiveData<List<Restaurant>> getRestaurantsData() {
        return restaurantsData;
    }

    public void start() {
        getRestaurantsListAsync();
    }

    private void getRestaurantsListAsync() {
        databaseRepository.getAllRestaurantsAsync(restaurants -> {
            restaurantsData.postValue(restaurants);
        });
    }
}
