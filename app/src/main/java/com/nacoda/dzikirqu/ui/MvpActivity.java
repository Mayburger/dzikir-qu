package com.nacoda.dzikirqu.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.nacoda.dzikirqu.base.BaseActivity;
import com.nacoda.dzikirqu.base.BasePresenter;
import com.nacoda.dzikirqu.libs.AppPreference;


/**
 * Created by Mayburger on 10/01/18.
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mvpPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
