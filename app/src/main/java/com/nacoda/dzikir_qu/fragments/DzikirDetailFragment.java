package com.nacoda.dzikir_qu.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nacoda.dzikir_qu.R;
import com.nacoda.dzikir_qu.adapters.ViewPagerAdapter;
import com.nacoda.dzikir_qu.base.BaseFragment;
import com.nacoda.dzikir_qu.room.database.AppDatabase;
import com.nacoda.dzikir_qu.room.utils.DatabaseInitializer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class DzikirDetailFragment extends BaseFragment {


    @BindView(R.id.indicator)
    CircleIndicator indicator;
    @BindView(R.id.pager)
    ViewPager pager;
    Unbinder unbinder;

    public DzikirDetailFragment() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pager.setAdapter(new ViewPagerAdapter(getActivity(), DatabaseInitializer.getDzikir(AppDatabase.getAppDatabase(getActivity())).get(1).getData()));
        indicator.setViewPager(pager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.next, R.id.back})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                pager.setCurrentItem(pager.getCurrentItem() + 1);
                break;
            case R.id.back:
                pager.setCurrentItem(pager.getCurrentItem() - 1);
                break;
        }
    }

    @Override
    public Fragment newInstance() {
        return new DzikirDetailFragment();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_dzikir_detail;
    }
}
