package com.nacoda.dzikirqu.libs;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nacoda.dzikirqu.model.quran.Data;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Mayburger on 2/8/18.
 */

public class QuranDataTypeConverter {


    @TypeConverter
    public static List<Data> stringToSomeObjectList(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Data>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Data> someObjects) {
        Gson gson = new Gson();
        return gson.toJson(someObjects);
    }

}
