package com.nacoda.dzikirqu.fragments.dzikir_quran;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.adapters.DzikirQuranAdapter;
import com.nacoda.dzikirqu.base.BaseFragment;
import com.nacoda.dzikirqu.constants.Prefs;
import com.nacoda.dzikirqu.model.quran.Data;
import com.nacoda.dzikirqu.ui.MvpFragment;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class DzikirQuranFragment extends BaseFragment {

    private List<Data> response;

    @SuppressLint("ValidFragment")
    public DzikirQuranFragment(List<Data> response) {
        this.response = response;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(new DzikirQuranAdapter(getActivity(), response));
        recycler.setNestedScrollingEnabled(false);
    }


    @Override
    public Fragment newInstance() {
        return new DzikirQuranFragment(response);
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_dzikir_quran;
    }


    @BindView(R.id.recycler)
    RecyclerView recycler;
}
