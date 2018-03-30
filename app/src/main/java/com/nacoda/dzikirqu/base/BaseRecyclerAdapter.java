package com.nacoda.dzikirqu.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import com.nacoda.dzikirqu.constants.Prefs;

import java.util.Locale;

/**
 * Created by Mayburger on 1/11/18.
 */

public class BaseRecyclerAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public Typeface getFont(Context context, String fontName) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName + ".ttf");
    }

    public int getStringInt(Context mContext,String resourceName) {
        String packageName = mContext.getPackageName();
        return mContext.getResources().getIdentifier(resourceName, "string", packageName);
    }

    @NonNull
    public String getLocalizedString(Activity context, int resource) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Configuration conf = context.getResources().getConfiguration();
        conf.locale = new Locale(preferences.getString(Prefs.LANGUAGE, Prefs.LANGUAGE_DEFAULT));
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Resources resources = new Resources(context.getAssets(), metrics, conf);
        return resources.getString(resource);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
