package com.nacoda.dzikirqu.fragments.dzikir_main;

import android.content.Context;
import com.nacoda.dzikirqu.base.BasePresenter;
import com.nacoda.dzikirqu.room.database.AppDatabase;
import com.nacoda.dzikirqu.room.utils.DatabaseInitializer;

/**
 * Created by Rosinante24 on 10/01/18.
 */

class DzikirMainPresenter extends BasePresenter<DzikirMainView> {
    DzikirMainPresenter(DzikirMainView view) {
        attachView(view);
    }

    void getData(Context mContext, String language) {
        mvpView.getData(DatabaseInitializer.getDzikirByLanguage(AppDatabase.getAppDatabase(mContext),language));
    }
}
