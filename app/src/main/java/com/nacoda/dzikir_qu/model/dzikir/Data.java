package com.nacoda.dzikir_qu.model.dzikir;

import com.google.gson.annotations.SerializedName;

@lombok.Data
public class Data {
    @SerializedName("arabic")
    private String arabic;
    @SerializedName("authenticity")
    private String authenticity;
    @SerializedName("english")
    private String english;
    @SerializedName("indonesia")
    private String indonesia;
    @SerializedName("notes_en")
    private String notes_en;
    @SerializedName("notes_id")
    private String notes_id;
    @SerializedName("source_en")
    private String source_en;
    @SerializedName("source_id")
    private String source_id;
}