package com.nacoda.dzikirqu.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by Mayburger on 3/16/18.
 */

@lombok.Data
public class ListModel {

    @SerializedName("id")
    private String id;

    public static ArrayList<ListModel> GsonBuilder(String response) {
        return new Gson().fromJson(response, new TypeToken<ArrayList<ListModel>>() {
        }.getType());
    }


}
