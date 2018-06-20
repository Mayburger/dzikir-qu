package com.nacoda.dzikirqu.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.constants.Prefs;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import butterknife.ButterKnife;

/**
 * Created by Rosinante24 on 10/01/18.
 */

public class BaseActivity extends AppCompatActivity {

    public Activity activity;

    public Typeface getFont(Context context, String fontName) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName + ".ttf");
    }

    @NonNull
    public String getLocalizedString(int resource) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        Locale savedLocale = conf.locale;
        conf.locale = new Locale(preferences.getString(Prefs.LANGUAGE,Prefs.LANGUAGE_DEFAULT));
        res.updateConfiguration(conf, null);
        String str = res.getString(resource);
        conf.locale = savedLocale;
        res.updateConfiguration(conf, null);
        return str;
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void addFragmentOnTop(@NotNull AppCompatActivity activity, Fragment fragment, int layout) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(layout, fragment)
                .addToBackStack(null)
                .commit();
    }

    public static void addFragmentOnTop(@NotNull AppCompatActivity activity, Fragment fragment, int layout, String id) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(layout, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        activity = this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        activity = this;
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
        activity = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
