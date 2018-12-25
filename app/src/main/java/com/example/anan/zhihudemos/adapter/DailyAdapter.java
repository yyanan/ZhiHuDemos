package com.example.anan.zhihudemos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anan.zhihudemos.R;
import com.example.anan.zhihudemos.beans.DailyBeforeListBean;
import com.example.anan.zhihudemos.beans.DailyListBean;
import com.example.anan.zhihudemos.utils.ImageLoader;
import com.example.anan.zhihudemos.utils.ZhihuDiffCallback;
import com.example.anan.zhihudemos.view.SquareImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TOP = 0; //滚动栏
    private static final int ITEM_DATE = 1;//日期
    private static final int ITEM_CONTENT = 2; //内容
    private List<DailyListBean.StoriesBean> storiesBeans;
    //滚动栏数据的设置
    private List<DailyListBean.TopStoriesBean> mTopList;
    private Context context;
    private LayoutInflater layoutInflater;
    private ViewPager topViewPager;
    //顶部循环
    private TopPagerAdapter mTopPagerAdapter;
    //默认时间设置
    private String currentTitle = "今日热闻";
    //默认是当前日期的数据
    private boolean isBefore = false;
    public DailyAdapter(List<DailyListBean.StoriesBean> storiesBeans, Context context) {
        this.storiesBeans = storiesBeans;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==ITEM_TOP){
            mTopPagerAdapter = new TopPagerAdapter(context,mTopList);
            return new TopViewHolder(layoutInflater.inflate(R.layout.item_top, null));
        }else if(viewType==ITEM_DATE) {
            return new DateViewHolder(layoutInflater.inflate(R.layout.item_date, parent, false));
        }
        return new ContentViewHolder(layoutInflater.inflate(R.layout.item_daily, parent, false));

    }

    @Override
    public int getItemViewType(int position) {
        if(isBefore){
            if(position == 0) {
                return ITEM_DATE;
            } else {
                return ITEM_CONTENT;
            }
        }else{
            if (position == 0) {
                return ITEM_TOP;
            } else if (position == 1) {
                return ITEM_DATE;
            } else {
                return ITEM_CONTENT;
            }
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ContentViewHolder){
            final int contentPosition;
            if(isBefore) {
                contentPosition = position - 1;
            } else {
                contentPosition = position - 2;
            }
            ((ContentViewHolder)holder).title.setText(storiesBeans.get(contentPosition).getTitle());
            ImageLoader.load(context,storiesBeans.get(contentPosition).getImages().get(0),((ContentViewHolder)holder).image);
        } else if (holder instanceof DateViewHolder) {
            ((DateViewHolder) holder).tvDate.setText(currentTitle);
        } else {
            ((TopViewHolder) holder).vpTop.setAdapter(mTopPagerAdapter);
            topViewPager= ((TopViewHolder) holder).vpTop;
        }
    }

    @Override
    public int getItemCount() {
        return storiesBeans.size();
    }


    /**
     * 内容ViewHodler
     */
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_daily_item_title)
        TextView title;
        @BindView(R.id.iv_daily_item_image)
        SquareImageView image;
        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 日期标题ViewHodler
     */
    public static class DateViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_daily_date)
        TextView tvDate;
        public DateViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 顶部轮播HodlerView
     */
    public static class TopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vp_top)
        ViewPager vpTop;
        @BindView(R.id.ll_point_container)
        LinearLayout llContainer;
        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 追加数据
     * @param info
     */
    public void addDailyDate(DailyListBean info) {
        currentTitle = "今日热闻";
        //需要了解
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ZhihuDiffCallback(storiesBeans, info.getStories()), true);
        storiesBeans = info.getStories();
        mTopList = info.getTop_stories();
        diffResult.dispatchUpdatesTo(this);
        isBefore=false;
    }

    /**
     * 根据指定日期追加更新数据
     * @param info
     */
    public void addDailyBeforeDate(DailyBeforeListBean info) {
        currentTitle = info.getDate();
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ZhihuDiffCallback(storiesBeans, info.getStories()), true);
        storiesBeans = info.getStories();
        isBefore = true;
        //通知UI更新
        diffResult.dispatchUpdatesTo(this);
    }
}
