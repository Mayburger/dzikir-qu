package com.nacoda.dzikirqu.model.quran;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.nacoda.dzikirqu.libs.QuranDataTypeConverter;

import java.util.List;

/**
 * Created by Mayburger on 3/25/18.
 */
@lombok.Data
@Entity(tableName = "quran")
public class QuranModel {

    @PrimaryKey(autoGenerate = true)
    private int ids;
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private String id;
    @ColumnInfo(name = "language")
    @SerializedName("language")
    private String language;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;
    @SerializedName("results")
    @TypeConverters(QuranDataTypeConverter.class)
    private List<Data> results;

    public static QuranModel GsonBuilder(String response) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(response, QuranModel.class);
    }

}
