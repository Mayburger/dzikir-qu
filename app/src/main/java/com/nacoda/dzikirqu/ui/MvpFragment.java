package com.nacoda.dzikirqu.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nacoda.dzikirqu.base.BaseFragment;
import com.nacoda.dzikirqu.base.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Rosinante24 on 10/01/18.
 */

public abstract class MvpFragment<P extends BasePresenter, F extends Fragment> extends BaseFragment<F> {

    protected P mvpPresenter;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentLayout(), container, false);
        mvpPresenter = createPresenter();
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    public abstract
    @LayoutRes
    int getFragmentLayout();

    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
            unbinder.unbind();
        }
    }
}


