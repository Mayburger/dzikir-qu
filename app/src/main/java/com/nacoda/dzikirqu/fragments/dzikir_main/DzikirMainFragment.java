package com.nacoda.dzikirqu.fragments.dzikir_main;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nacoda.dzikirqu.mvp.DetailActivity;
import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.adapters.DzikirMainAdapter;
import com.nacoda.dzikirqu.constants.Fonts;
import com.nacoda.dzikirqu.constants.Prefs;
import com.nacoda.dzikirqu.model.dzikir.DzikirModel;
import com.nacoda.dzikirqu.ui.MvpFragment;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DzikirMainFragment extends MvpFragment<DzikirMainPresenter, Fragment> implements DzikirMainView {

    @BindView(R.id.root)
    View root;

    public DzikirMainFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initTextView();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mvpPresenter.getData(getActivity(), preferences.getString(Prefs.LANGUAGE, Prefs.LANGUAGE_DEFAULT));
    }

    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.description)
    TextView description;

    void initTextView() {
        subtitle.setText(getLocalizedString(R.string.main_subtitle));
        subtitle.setTypeface(getFont(getActivity(), Fonts.SERIFBOLD));
        description.setText(getLocalizedString(R.string.main_description));
        description.setTypeface(getFont(getActivity(), Fonts.ROBOLIGHT));
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
