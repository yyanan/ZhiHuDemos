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
import com.example.anan.zhihudemos.beans.DailyListBean;
import com.example.anan.zhihudemos.beans.SectionChildListBean;
import com.example.anan.zhihudemos.beans.SectionListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class SpecialAdapter extends XRecyclerView.Adapter<SpecialAdapter.ViewHolder>{


    private final List<SectionListBean.DataBean> mList;
    private final Activity activity;

    public SpecialAdapter(List<SectionListBean.DataBean> mList, Activity activity) {

        this.mList = mList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_item,null,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.section_des.setText(mList.get(position).getDescription());
        holder.section_kind.setText(mList.get(position).getName());
        Glide.with(activity).load(mList.get(position).getThumbnail()).into(holder.section_bg);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView section_bg;
        private final TextView section_des;
        private final TextView section_kind;

        public ViewHolder(View itemView) {
            super(itemView);
            section_bg=itemView.findViewById(R.id.section_bg);
            section_des=itemView.findViewById(R.id.section_des);
            section_kind=itemView.findViewById(R.id.section_kind);
        }
    }
}
