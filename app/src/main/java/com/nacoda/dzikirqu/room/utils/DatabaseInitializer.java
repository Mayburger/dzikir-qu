package com.nacoda.dzikirqu.room.utils;

import com.nacoda.dzikirqu.model.dzikir.DzikirModel;
import com.nacoda.dzikirqu.model.quran.QuranModel;
import com.nacoda.dzikirqu.room.database.AppDatabase;

import java.util.List;

public class DatabaseInitializer {

    public static void insertDzikirData(final AppDatabase db, List<DzikirModel> data) {
        db.dzikirDao().insertDzikir(data);
    }

    public static DzikirModel getDzikirByIdAndLanguage(final AppDatabase db, String id, String language) {
        return db.dzikirDao().getDzikirByIdAndLanguage(id, language);
    }

    public static List<DzikirModel> getDzikirByLanguage(final AppDatabase db, String language) {
        return db.dzikirDao().getDzikirByLanguage(language);
    }

    public static void insertQuranData(final AppDatabase db, List<QuranModel> data) {
        db.quranDao().insertQuran(data);
    }
    public static List<QuranModel> getQuran(final AppDatabase db) {
        return  db.quranDao().getQuran();
    }

    public static QuranModel getQuranByIdAndLanguage(final AppDatabase db, String id, String language) {
        return db.quranDao().getQuranByIdAndLanguage(id, language);
    }

}
