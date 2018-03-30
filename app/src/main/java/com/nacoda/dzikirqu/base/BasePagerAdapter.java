package com.nacoda.dzikirqu.base;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.InputStream;

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

    public String getStringJson(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("json/quran/en/al-mulk.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}

