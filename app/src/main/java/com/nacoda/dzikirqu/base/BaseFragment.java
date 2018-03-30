package com.nacoda.dzikirqu.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nacoda.dzikirqu.constants.Prefs;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import butterknife.ButterKnife;

/**
 * Created by Rosinante24 on 10/01/18.
 */

public abstract class BaseFragment<F extends Fragment> extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public String getStringJson(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("json/quran/en/al-mulk.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    @NonNull
    public String getLocalizedString(int resource) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Configuration conf = getResources().getConfiguration();
        conf.locale = new Locale(preferences.getString(Prefs.LANGUAGE, Prefs.LANGUAGE_DEFAULT));
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Resources resources = new Resources(getActivity().getAssets(), metrics, conf);
        return resources.getString(resource);
    }

    public int getStringInt(Context mContext,String resourceName) {
        String packageName = mContext.getPackageName();
        return mContext.getResources().getIdentifier(resourceName, "string", packageName);
    }

    public Typeface getFont(Context context, String fontName) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName + ".ttf");
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

    public abstract F newInstance();

    public abstract
    @LayoutRes
    int getFragmentLayout();

}
