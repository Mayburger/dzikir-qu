package com.nacoda.dzikirqu.model.quran;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mayburger on 3/25/18.
 */

@lombok.Data
public class Data {
    @SerializedName("arabic")
    public String arabic;
    @SerializedName("translation")
    public String translation;
}
