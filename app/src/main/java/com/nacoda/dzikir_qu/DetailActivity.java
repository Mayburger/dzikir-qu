package com.nacoda.dzikir_qu;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nacoda.dzikir_qu.adapters.ViewPagerAdapter;
import com.nacoda.dzikir_qu.base.BaseActivity;
import com.nacoda.dzikir_qu.fragments.DzikirDetailFragment;
import com.nacoda.dzikir_qu.room.database.AppDatabase;
import com.nacoda.dzikir_qu.room.utils.DatabaseInitializer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        addFragmentOnTop(DetailActivity.this,new DzikirDetailFragment(),R.id.frame_detail);

    }
}
