
package com.nacoda.dzikir_qu.model;

import java.util.List;

import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.nacoda.dzikir_qu.model.dzikir.Dzikir;

import lombok.Data;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
@Data
public class BaseModel {

    @SerializedName("results")
    private List<Dzikir> results;

    public static BaseModel GsonBuilder(String response) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(response, BaseModel.class);
    }

}
