package com.nacoda.dzikirqu.mvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.base.BaseActivity;
import com.nacoda.dzikirqu.constants.Fonts;
import com.nacoda.dzikirqu.fragments.dzikir_main.DzikirMainFragment;
import com.nacoda.dzikirqu.libs.AppPreference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @BindView(R.id.drawer)
    FlowingDrawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_FULLSCREEN);
        initDrawerText();
        addFragmentOnTop(this, new DzikirMainFragment(), R.id.frame_main);
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

    @BindView(R.id.settings_text)
    TextView settingsText;
//    @BindView(R.id.rate_text)
//    TextView rateText;
    @BindView(R.id.about_text)
    TextView aboutText;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.drawer_parent)
    RelativeLayout drawerParent;

    void initDrawerText() {
        title.setTypeface(getFont(getApplicationContext(), Fonts.MONTSERRATBLACK));
        settingsText.setText(getLocalizedString(R.string.settings));
        settingsText.setTypeface(getFont(getApplicationContext(), Fonts.ROBOLIGHT));
//        rateText.setText(getLocalizedString(R.string.rate));
//        rateText.setTypeface(getFont(getApplicationContext(), Fonts.ROBOLIGHT));
        aboutText.setText(getLocalizedString(R.string.about));
        aboutText.setTypeface(getFont(getApplicationContext(), Fonts.ROBOLIGHT));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @BindView(R.id.drawer_button)
    ImageView drawerButton;
    @OnClick({R.id.drawer_button, R.id.settings})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.drawer_button:
                mDrawer.openMenu();
                break;
            case R.id.settings:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                Intent settings = new Intent(this, SettingsActivity.class);
                settings.putExtra(getString(R.string.pref_language_key), prefs.getString(getString(R.string.pref_language_key), getString(R.string.english_value)));
                startActivity(settings);
                break;
        }
    }
}
