package com.nacoda.dzikirqu.adapters;

import android.app.Activity;
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
import com.nacoda.dzikirqu.model.dzikir.DzikirModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mayburger on 10/19/17.
 */

public class DzikirMainAdapter extends BaseRecyclerAdapter<DzikirMainAdapter.ViewHolder> {

    private Activity mContext;
    private List<DzikirModel> data;
    private final OnItemClickListener listener;

    public DzikirMainAdapter(Activity mContext, List<DzikirModel> data, OnItemClickListener listener) {
        this.mContext = mContext;
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.row_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(getLocalizedString(mContext, getStringInt(mContext, data.get(position).getId())));
        holder.title.setTypeface(getFont(mContext, Fonts.QUICKBOLD));
        holder.count.setText(data.get(position).getData().size()+ " Dzikir");
        holder.count.setTypeface(getFont(mContext, Fonts.QUICKBOOK));
        holder.icon.setImageResource(mContext.getResources().getIdentifier(data.get(position).getId() + "_icon", "drawable", mContext.getPackageName()));
        holder.click(data.get(position), listener, holder.root, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onClick(DzikirModel data, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.root)
        View root;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.icon)
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void click(final DzikirModel data, final OnItemClickListener listener, View view, final int position) {
            view.setOnClickListener(v -> listener.onClick(data, position));
        }
    }
}
