package com.nacoda.dzikirqu.fragments.dzikir_main;

import com.nacoda.dzikirqu.base.BaseView;
import com.nacoda.dzikirqu.model.dzikir.DzikirModel;

import java.util.List;

/**
 * Created by Mayburger on 10/01/18.
 */

public interface DzikirMainView extends BaseView {
    void getData(List<DzikirModel> response);
}
