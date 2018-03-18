package com.nacoda.dzikir_qu.base;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nacoda.dzikir_qu.R;
import com.nacoda.dzikir_qu.model.dzikir.Data;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Mayburger on 3/16/18.
 */

public class BasePagerAdapter extends PagerAdapter {

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    protected Typeface getFont(Context context, String fontName){
        return Typeface.createFromAsset(context.getAssets(),"fonts/"+fontName+".ttf");
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}

