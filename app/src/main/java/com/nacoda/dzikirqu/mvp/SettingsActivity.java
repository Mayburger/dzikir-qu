package com.nacoda.dzikirqu.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.base.BaseActivity;
import com.nacoda.dzikirqu.libs.AppPreference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        onSetupToolbar();
    }

    public void onSetupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle(getLocalizedString(R.string.settings));
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
}
