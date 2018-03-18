package com.nacoda.dzikir_qu.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nacoda.dzikir_qu.R;
import com.nacoda.dzikir_qu.base.BasePagerAdapter;
import com.nacoda.dzikir_qu.model.dzikir.Data;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mayburger on 3/16/18.
 */

public class ViewPagerAdapter extends BasePagerAdapter {

    private Context context;
    private List<Data> data;

    public ViewPagerAdapter(Context context, List<Data> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @BindView(R.id.arabic)
    TextView arabic;
    @BindView(R.id.translation)
    TextView translation;

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dzikir_pager, container, false);
        ButterKnife.bind(this,v);

        arabic.setText(data.get(position).getArabic());
//        arabic.setTypeface(getFont(context,"uthman"));

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

