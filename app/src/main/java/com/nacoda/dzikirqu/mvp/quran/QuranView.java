package com.nacoda.dzikirqu.mvp.quran;

import com.nacoda.dzikirqu.base.BaseView;
import com.nacoda.dzikirqu.model.quran.Data;

import java.util.List;

/**
 * Created by Mayburger on 10/01/18.
 */

public interface QuranView extends BaseView {
    void getData(List<Data> response, String surahName);
    void onFinishDownloading(String audioName);
}
