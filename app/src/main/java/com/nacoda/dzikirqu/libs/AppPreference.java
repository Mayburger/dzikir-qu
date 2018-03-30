package com.nacoda.dzikirqu.libs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.constants.Prefs;


/**
 * Created by dicoding on 12/6/2016.
 */

public class AppPreference {

    private SharedPreferences dPrefs, mPrefs;
    private Context context;

    public AppPreference(Context context) {
        dPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mPrefs = context.getSharedPreferences(Prefs.IsMainActive, 0);
        this.context = context;
    }

    public void setMainActive(Boolean data) {
        SharedPreferences.Editor editor = mPrefs.edit();
        String key = Prefs.IsMainActive;
        editor.putBoolean(key, data);
        editor.commit();
    }

    public void setFirstRun(Boolean input) {
        SharedPreferences.Editor editor = dPrefs.edit();
        String key = context.getResources().getString(R.string.first_run);
        editor.putBoolean(key, input);
        editor.commit();
    }

    public Boolean getFirstRun() {
        String key = context.getResources().getString(R.string.first_run);
        return dPrefs.getBoolean(key, true);
    }

    public Boolean isMainActive() {
        String key = Prefs.IsMainActive;
        return mPrefs.getBoolean(key, true);
    }
}