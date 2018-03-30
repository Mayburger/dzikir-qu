package com.nacoda.dzikirqu.fragments.dzikir_detail;

import com.nacoda.dzikirqu.base.BaseView;
import com.nacoda.dzikirqu.model.dzikir.Data;

import java.util.List;

/**
 * Created by Mayburger on 10/01/18.
 */

public interface DzikirDetailView extends BaseView {
    void getData(List<Data> response);
    void onFinishDownloading(String audioName);
}
