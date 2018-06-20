package com.nacoda.dzikirqu.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.base.BaseActivity;
import com.nacoda.dzikirqu.constants.Fonts;
import com.nacoda.dzikirqu.libs.AppPreference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        onSetupToolbar();
    }

    public void onSetupToolbar() {
        title.setText(getLocalizedString(R.string.settings));
        title.setTypeface(getFont(getApplicationContext(), Fonts.ROBONORMAL));
    }

    void onBack(){

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

    @OnClick({R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                AppPreference prefs = new AppPreference(getApplicationContext());
                if (prefs.isMainActive()) {
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
                break;
        }
    }
}
