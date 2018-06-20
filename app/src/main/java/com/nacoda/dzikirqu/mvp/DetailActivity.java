package com.nacoda.dzikirqu.mvp;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.base.BaseActivity;
import com.nacoda.dzikirqu.constants.Prefs;
import com.nacoda.dzikirqu.fragments.dzikir_detail.DzikirDetailFragment;

import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        addFragmentOnTop(DetailActivity.this, new DzikirDetailFragment(), R.id.frame_detail);

        overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);

        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
        }
    }

    void initTheme() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        setTheme(getResources().getIdentifier("Detail.Theme." + preferences.getString(Prefs.THEME, Prefs.THEME_DEFAULT), "style", getApplicationContext().getPackageName()));
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
