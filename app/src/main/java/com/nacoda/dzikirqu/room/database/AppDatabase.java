package com.nacoda.dzikirqu.room.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.nacoda.dzikirqu.libs.QuranDataTypeConverter;
import com.nacoda.dzikirqu.model.dzikir.DzikirModel;
import com.nacoda.dzikirqu.model.quran.QuranModel;
import com.nacoda.dzikirqu.room.dao.DzikirDao;
import com.nacoda.dzikirqu.room.dao.QuranDao;

@Database(entities = {DzikirModel.class, QuranModel.class}, version = 9)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract DzikirDao dzikirDao();
    public abstract QuranDao quranDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "hd")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
