package com.nacoda.dzikir_qu.model.dzikir;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.nacoda.dzikir_qu.libs.typeconverters.DzikirDataTypeConverter;

import java.util.List;
/**
 * Created by Mayburger on 3/16/18.
 */

@lombok.Data
@Entity(tableName = "dzikir")
public class Dzikir {

    @PrimaryKey(autoGenerate = true)
    private int ids;
    @SerializedName("data")
    @TypeConverters(DzikirDataTypeConverter.class)
    private List<Data> data;
    @ColumnInfo(name = "title")
    @SerializedName("id")
    private String id;

}
