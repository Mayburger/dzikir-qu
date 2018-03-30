package com.nacoda.dzikirqu.model.dzikir;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.nacoda.dzikirqu.libs.DzikirDataTypeConverter;

import java.util.List;
/**
 * Created by Mayburger on 3/16/18.
 */

@lombok.Data
@Entity(tableName = "dzikir")
public class DzikirModel {

    @PrimaryKey(autoGenerate = true)
    private int ids;
    @SerializedName("results")
    @TypeConverters(DzikirDataTypeConverter.class)
    private List<Data> data;
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private String id;
    @ColumnInfo(name = "language")
    @SerializedName("language")
    private String language;

    public static DzikirModel GsonBuilder(String response) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(response, DzikirModel.class);
    }


}
