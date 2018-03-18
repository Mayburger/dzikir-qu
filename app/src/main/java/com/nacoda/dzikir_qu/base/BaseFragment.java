package com.nacoda.dzikir_qu.base;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.jetbrains.annotations.NotNull;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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

    public Typeface getFont(Context context, String fontName){
        return Typeface.createFromAsset(context.getAssets(),"fonts/"+fontName+".ttf");
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
