package com.nacoda.dzikirqu.constants;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Mayburger on 3/18/18.
 */

public class Fonts {
    public static String ARABIC = "uthman";

    public static String ROBOLIGHT = "roboto_light";
    public static String ROBOTHIN = "roboto_thin";
    public static String ROBONORMAL = "roboto_regular";
    public static String ROBOMEDIUM = "roboto_medium";
    public static String QUICKBOOK = "quicksand_book";
    public static String QUICKBOLD = "quicksand_bold";
    public static String SERIF = "serif";
    public static String SERIFBOLD = "serif_bold";
    public static String MONTSERRATBLACK = "montserrat_black";


    public static void init(Context context, View rootView, String fontName) {
        try {
            if (rootView instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) rootView;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    init(context, child, fontName);
                }
            } else if (rootView instanceof TextView) {
                ((TextView) rootView).setTypeface(getFont(context, fontName));
            }
        } catch (Exception ignored) {
        }
    }

    private static Typeface getFont(Context context, String fontName) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName + ".ttf");
    }

}
