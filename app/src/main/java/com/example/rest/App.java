package com.example.rest;

import android.app.Application;
import android.util.Log;

import com.example.rest.data.database.GetTableDatabase;
import com.example.rest.data.repository.DatabaseRepository;
import com.example.rest.presentation.mainpage.MainPageFragment;

public class App extends Application {

    private static GetTableDatabase database;
    public static DatabaseRepository databaseRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        database = GetTableDatabase.getInstance(getApplicationContext());
        if (database == null) {
            // Обработка ошибки, например, вывод сообщения в лог или уведомление
            Log.e("App", "Database initialization failed.");
            return;
        }

        databaseRepository = new DatabaseRepository(database);

        if (databaseRepository == null) {
            // Обработка ошибки, например, вывод сообщения в лог или уведомление
            Log.e("App", "DatabaseRepository initialization failed.");
            return;
        }

        // Добавим логирование для проверки инициализации
        Log.d("App", "Database and DatabaseRepository initialized successfully.");
    }

    public static GetTableDatabase getDatabase() {
        return database;
    }

    public static DatabaseRepository getDatabaseRepository() {
        return databaseRepository;
    }
}

