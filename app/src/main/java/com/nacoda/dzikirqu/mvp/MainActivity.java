package com.nacoda.dzikirqu.mvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.base.BaseActivity;
import com.nacoda.dzikirqu.constants.Fonts;
import com.nacoda.dzikirqu.constants.Prefs;
import com.nacoda.dzikirqu.fragments.dzikir_main.DzikirMainFragment;
import com.nacoda.dzikirqu.libs.AppPreference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        addFragmentOnTop(this, new DzikirMainFragment(), R.id.frame_main);
    }

    void initTheme() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        setTheme(getResources().getIdentifier("Main.Theme." + preferences.getString(Prefs.THEME, Prefs.THEME_DEFAULT), "style", getApplicationContext().getPackageName()));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
        AppPreference prefs = new AppPreference(getApplicationContext());
        prefs.setMainActive(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
        AppPreference prefs = new AppPreference(getApplicationContext());
        prefs.setMainActive(false);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
