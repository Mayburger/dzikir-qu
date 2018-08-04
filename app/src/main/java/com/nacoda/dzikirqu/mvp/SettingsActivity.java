package com.nacoda.dzikirqu.mvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.base.BaseActivity;
import com.nacoda.dzikirqu.constants.Fonts;
import com.nacoda.dzikirqu.constants.Prefs;
import com.nacoda.dzikirqu.libs.AppPreference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        onSetupToolbar();
    }

    private void onSetupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getLocalizedString(R.string.settings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    void initTheme() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        setTheme(getResources().getIdentifier("Settings.Theme." + preferences.getString(Prefs.THEME, Prefs.THEME_DEFAULT), "style", getApplicationContext().getPackageName()));
    }


    @Override
    public void onBackPressed() {
        AppPreference prefs = new AppPreference(getApplicationContext());
        if (prefs.isMainActive()) {
            finish();
        } else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        AppPreference prefs = new AppPreference(getApplicationContext());
        if (prefs.isMainActive()) {
            finish();
        } else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        return super.onSupportNavigateUp();
    }
}
