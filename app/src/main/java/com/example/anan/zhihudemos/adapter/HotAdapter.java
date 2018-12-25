package com.example.anan.zhihudemos.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anan.zhihudemos.R;
import com.example.anan.zhihudemos.beans.HotListBean;

import java.util.List;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.VihewHolder> {
    private final List<HotListBean.RecentBean> mList;
    private final Activity activity;

    public HotAdapter(List<HotListBean.RecentBean> mList, Activity activity) {

        this.mList = mList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public VihewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_item,null);
        VihewHolder vihewHolder = new VihewHolder(view);
        return vihewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VihewHolder holder, int position) {
        holder.tv.setText(mList.get(position).getTitle());
        Glide.with(activity).load(mList.get(position).getThumbnail()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class VihewHolder extends RecyclerView.ViewHolder {
        private final ImageView img;
        private final TextView tv;

        public VihewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.iv_daily_item_image);
            tv=itemView.findViewById(R.id.tv_daily_item_title);
        }
    }
}
