package com.nacoda.dzikirqu.model.dzikir;

import com.google.gson.annotations.SerializedName;

@lombok.Data
public class Data {
    @SerializedName("arabic")
    private String arabic;
    @SerializedName("translation")
    private String translation;
    @SerializedName("source")
    private String source;
    @SerializedName("bismillah")
    private String bismillah;
    @SerializedName("recite")
    private String recite;
    @SerializedName("audio")
    private String audio;
    @SerializedName("audio_name")
    private String audio_name;
    @SerializedName("surah")
    private String surah;
}