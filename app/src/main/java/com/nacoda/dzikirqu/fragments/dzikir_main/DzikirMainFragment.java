package com.nacoda.dzikirqu.fragments.dzikir_main;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.nacoda.dzikirqu.mvp.DetailActivity;
import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.adapters.DzikirMainAdapter;
import com.nacoda.dzikirqu.constants.Fonts;
import com.nacoda.dzikirqu.constants.Prefs;
import com.nacoda.dzikirqu.model.dzikir.DzikirModel;
import com.nacoda.dzikirqu.mvp.SettingsActivity;
import com.nacoda.dzikirqu.ui.MvpFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class    DzikirMainFragment extends MvpFragment<DzikirMainPresenter, Fragment> implements DzikirMainView {

    @BindView(R.id.root)
    View root;

    PopupWindow popupWindow;

    public DzikirMainFragment() {
    }

    View v;

    @SuppressLint("InflateParams")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initTextView();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mvpPresenter.getData(getActivity(), preferences.getString(Prefs.LANGUAGE, Prefs.LANGUAGE_DEFAULT));
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        v = inflater.inflate(R.layout.menu_popup_main, null);
        initPopupWindow();
    }


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.description)
    TextView description;

    void initTextView() {
        subtitle.setText(getLocalizedString(R.string.main_subtitle));
        subtitle.setTypeface(getFont(getActivity(), Fonts.SERIFBOLD));
        description.setText(getLocalizedString(R.string.main_description));
        description.setTypeface(getFont(getActivity(), Fonts.ROBOLIGHT));
        title.setTypeface(getFont(getActivity(), Fonts.SIMPLYROUNDED));
    }

    void initPopupWindow(){
        popupWindow = new PopupWindow(
                v,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
    }


    @BindView(R.id.drawer_button)
    ImageView drawerButton;

    @OnClick({R.id.drawer_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.drawer_button:
                showPopup();
                break;
        }
    }

    TextView settingsText;
    TextView aboutText;
    LinearLayout settings;
    void showPopup() {
        if (Build.VERSION.SDK_INT >= 21) {
            popupWindow.setElevation(5.0f);
        }
        popupWindow.showAsDropDown(drawerButton);
        settingsText = v.findViewById(R.id.settings_text);
        settingsText.setText(getLocalizedString(R.string.settings));
        settingsText.setTypeface(getFont(getActivity(), Fonts.MONTSERRATLIGHT));
        aboutText = v.findViewById(R.id.about_text);
        aboutText.setText(getLocalizedString(R.string.about));
        aboutText.setTypeface(getFont(getActivity(), Fonts.MONTSERRATLIGHT));
        settings = v.findViewById(R.id.settings);

        settings.setOnClickListener(view -> {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            Intent settings = new Intent(getActivity(), SettingsActivity.class);
            settings.putExtra(getString(R.string.pref_language_key), prefs.getString(getString(R.string.pref_language_key), getString(R.string.english_value)));
            startActivity(settings);
            popupWindow.dismiss();
        });
    }


    @Override
    public Fragment newInstance() {
        return new DzikirMainFragment();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_dzikir_main;
    }

    @Override
    protected DzikirMainPresenter createPresenter() {
        return new DzikirMainPresenter(DzikirMainFragment.this);
    }

    @BindView(R.id.recycler_main)
    RecyclerView recyclerMain;

    @Override
    public void getData(List<DzikirModel> response) {
        recyclerMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerMain.setAdapter(new DzikirMainAdapter(getActivity(), response, (data, position) -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(getString(R.string.id), data.getId());
            startActivity(intent);
        }));
    }
}
