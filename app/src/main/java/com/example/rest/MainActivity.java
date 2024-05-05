package com.example.rest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.rest.data.model.RestaurantModel;
import com.example.rest.data.repository.DatabaseRepository;
import com.example.rest.presentation.mainpage.MainPageFragment;

public class MainActivity extends AppCompatActivity {

    public DatabaseRepository databaseRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseRepository = App.getDatabaseRepository();
        if (databaseRepository != null)
            databaseRepository.deleteAll();
        else
            Log.e("Проверка", "db равен null");
        addMockRestaurantsToDB();
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
    }

    private void addMockRestaurantsToDB() {
        databaseRepository.insertRestaurantsAsync(
                new RestaurantModel(
                        1,
                        "Four",
                        "lila",
                        "Мира, 45б Four Elements Perm, Пермь 614022 Россия",
                        "12:00–23:59",
                        "Итальянская, Морепродукты, Средиземноморская, Европейская, Русская, Центральноевропейская, Международная"
                ),
                new RestaurantModel(
                        2,
                        "Ле Марш",
                        "jpan",
                        "Газета Звезда ул., д. 27 (3 этаж) , Пермь 614000 Россия",
                        "00:00–12:00",
                        "Морепродукты, Европейская, Восточноевропейская, Французская, Средиземноморская"
                ),
                new RestaurantModel(
                        3,
                        "Наири",
                        "good_girl",
                        "Советская, 67, Пермь 614000 Россия",
                        "09:00–00:00",
                        "Бельгийская, Русская, Восточноевропейская, Европейская"
                ),
                new RestaurantModel(
                        4,
                        "La Bottega.VS",
                        "ku",
                        " ул. Советская, 62, Пермь 614000 Россия",
                        "00:00–23:00",
                        "Европейская, Итальянская, Средиземноморская"
                ),
                new RestaurantModel(
                        5,
                        "Old Moose",
                        "white_rabbit",
                        "Комсомольский Проспект, 14, Пермь 614000 Россия",
                        "16:00–01:00",
                        "Бар, Паб, Пивные рестораны"
                )
        );
    }

}