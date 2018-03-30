package com.nacoda.dzikirqu.adapters;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacoda.dzikirqu.R;
import com.nacoda.dzikirqu.base.BaseRecyclerAdapter;
import com.nacoda.dzikirqu.constants.Fonts;
import com.nacoda.dzikirqu.constants.Prefs;
import com.nacoda.dzikirqu.model.quran.Data;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mayburger on 10/19/17.
 */

public class DzikirQuranAdapter extends BaseRecyclerAdapter<DzikirQuranAdapter.ViewHolder> {

    private Activity mContext;
    private List<Data> data;

    public DzikirQuranAdapter(Activity mContext, List<Data> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.row_quran, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (position % 2 == 0) {
            holder.root.setBackgroundColor(mContext.getResources().getColor(R.color.colorGreyOdd));
        } else {
            holder.root.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        if (preferences.getString(Prefs.QALIGNMENT, Prefs.QALIGNMENT_DEFAULT).equals(Prefs.QALIGNMENT_DEFAULT)) {
            holder.arabic.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.translation.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        } else {
            holder.arabic.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            holder.translation.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }

        holder.count.setTypeface(getFont(mContext, Fonts.ROBOLIGHT));
        holder.count.setText(String.valueOf(position + 1));
        holder.arabic.setText(data.get(position).getArabic());
        holder.arabic.setTypeface(getFont(mContext, Fonts.ARABIC));
        setFontSize(holder.arabic, preferences.getString(Prefs.FONTSIZE, Prefs.FONTSIZE_DEFAULT));
        holder.translation.setText(data.get(position).getTranslation());
        holder.translation.setTypeface(getFont(mContext, Fonts.ROBOLIGHT));
        setFontSize(holder.translation, preferences.getString(Prefs.FONTSIZE, Prefs.FONTSIZE_DEFAULT));
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

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.arabic)
        TextView arabic;
        @BindView(R.id.translation)
        TextView translation;
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.root)
        View root;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
