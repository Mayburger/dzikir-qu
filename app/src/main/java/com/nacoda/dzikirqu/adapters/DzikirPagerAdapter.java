package com.nacoda.dzikirqu.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nacoda.dzikirqu.mvp.quran.QuranActivity;
import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.base.BasePagerAdapter;
import com.nacoda.dzikirqu.constants.Fonts;
import com.nacoda.dzikirqu.constants.Prefs;
import com.nacoda.dzikirqu.model.dzikir.Data;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mayburger on 3/16/18.
 */

public class DzikirPagerAdapter extends BasePagerAdapter {

    private Activity context;
    private List<Data> data;

    public DzikirPagerAdapter(Activity context, List<Data> data) {
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
    @BindView(R.id.surah)
    ImageView surah;
    @BindView(R.id.recite)
    TextView recite;
    @BindView(R.id.source)
    TextView source;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.readButton)
    RelativeLayout readButton;
    @BindView(R.id.read)
    TextView read;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View v = inflater.inflate(R.layout.dzikir_pager, container, false);
        ButterKnife.bind(this, v);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        arabic.setText(data.get(position).getArabic());

        if (data.get(position).getSurah() != null) {
            arabic.setTypeface(getFont(context, Fonts.QUICKBOLD));
        } else {
            arabic.setTypeface(getFont(context, Fonts.ARABIC));
        }

        setFontSize(arabic, preferences.getString(Prefs.FONTSIZE, Prefs.FONTSIZE_DEFAULT));

        if (data.get(position).getSurah() != null) {
            read.setText(getLocalizedString(context, R.string.read));
            readButton.setVisibility(View.VISIBLE);
            translation.setVisibility(View.GONE);
            divider.setVisibility(View.GONE);
            readButton.setOnClickListener(view -> onReadButtonClicked(data.get(position).getAudio_name(), data.get(position).getAudio(), data.get(position).getSurah()));
        } else {
            translation.setText(data.get(position).getTranslation());
            translation.setTypeface(getFont(context, Fonts.ROBOLIGHT));
            setFontSize(translation, preferences.getString(Prefs.FONTSIZE, Prefs.FONTSIZE_DEFAULT));
        }

        source.setText(data.get(position).getSource());
        setFontSize(source, preferences.getString(Prefs.FONTSIZE, Prefs.FONTSIZE_DEFAULT));
        recite.setText(data.get(position).getRecite());
        setFontSize(recite, preferences.getString(Prefs.FONTSIZE, Prefs.FONTSIZE_DEFAULT));

        source.setTypeface(getFont(context, Fonts.ROBOTHIN));

        if (Boolean.parseBoolean(data.get(position).getBismillah())) {
            surah.setVisibility(View.VISIBLE);
        } else {
            surah.setVisibility(View.GONE);
        }

        container.addView(v);
        return v;
    }

    private void onReadButtonClicked(String audio_name, String audio_url, String id) {
        onPressedBounce(readButton, context);
        Intent i = new Intent(context, QuranActivity.class);
        i.putExtra(context.getResources().getString(R.string.audio_name), audio_name);
        i.putExtra(context.getResources().getString(R.string.audio_url), audio_url);
        i.putExtra(context.getResources().getString(R.string.id), id);
        context.startActivity(i);
    }

    private void setFontSize(TextView view, String size) {
        switch (view.getId()) {
            case R.id.arabic:
                switch (size) {
                    case "small":
                        view.setTextSize(24);
                        break;
                    case "medium":
                        view.setTextSize(28);
                        break;
                    case "large":
                        view.setTextSize(32);
                        break;
                }
                break;
            default:
                switch (size) {
                    case "small":
                        view.setTextSize(14);
                        break;
                    case "medium":
                        view.setTextSize(18);
                        break;
                    case "large":
                        view.setTextSize(22);
                        break;
                }
        }

    }

    @NonNull
    public String getLocalizedString(Context context, int resource) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Resources res = context.getResources();
        Configuration conf = res.getConfiguration();
        Locale savedLocale = conf.locale;
        conf.locale = new Locale(preferences.getString(Prefs.LANGUAGE, Prefs.LANGUAGE_DEFAULT));
        res.updateConfiguration(conf, null);
        String str = res.getString(resource);
        conf.locale = savedLocale;
        res.updateConfiguration(conf, null);
        return str;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((NestedScrollView) object);
    }
}

