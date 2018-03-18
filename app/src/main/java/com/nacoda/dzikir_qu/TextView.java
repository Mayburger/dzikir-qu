package com.nacoda.dzikir_qu;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Mayburger on 3/18/18.
 */

public class TextView extends android.support.v7.widget.AppCompatTextView {

    public TextView(Context context) {
        super(context);
    }

    public TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/uthman.ttf"));
    }
}
