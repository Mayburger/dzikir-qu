package com.nacoda.dzikir_qu.libs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.nacoda.dzikir_qu.R;


/**
 * Created by dicoding on 12/6/2016.
 */

public class AppPreference {

    SharedPreferences prefs;
    Context context;

    public AppPreference(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFirstRun(Boolean input) {

        SharedPreferences.Editor editor = prefs.edit();
        String key = context.getResources().getString(R.string.first_run);
        editor.putBoolean(key, input);
        editor.commit();
    }

    public Boolean getFirstRun() {
        String key = context.getResources().getString(R.string.first_run);
        return prefs.getBoolean(key, true);
    }
}