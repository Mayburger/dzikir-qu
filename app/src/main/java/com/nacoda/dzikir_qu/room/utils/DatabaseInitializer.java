package com.nacoda.dzikir_qu.room.utils;

import com.nacoda.dzikir_qu.model.dzikir.Dzikir;
import com.nacoda.dzikir_qu.room.database.AppDatabase;

import java.util.List;

public class DatabaseInitializer {


    public static List<Dzikir> getDzikir(final AppDatabase db) {
        return db.dzikirDao().getDzikir();
    }

    public static void insertQuranData(final AppDatabase db, List<Dzikir> data) {
        db.dzikirDao().insertDzikir(data);
    }

}
