package com.nacoda.dzikirqu.model;

import android.app.ProgressDialog;
import android.content.Context;

import lombok.Data;

/**
 * Created by Mayburger on 3/20/18.
 */

@Data
public class Download {

    String name;
    String url;
    Context context;

    public Download(String name, String url, Context context) {
        this.name = name;
        this.url = url;
        this.context = context;
    }
}
