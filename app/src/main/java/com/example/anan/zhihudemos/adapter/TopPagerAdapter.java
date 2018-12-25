package com.example.anan.zhihudemos.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anan.zhihudemos.R;
import com.example.anan.zhihudemos.beans.DailyListBean;
import com.example.anan.zhihudemos.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * 项目名：xiaoshixun
 * 包名：  notice.liangxq.com.mymvp.adapter
 * 文件名：TopPagerAdapter
 * 创建者：mand
 * 创建时间：2018/12/24  19:58
 * 描述：TODO
 *
 * 日报顶部滚动栏Adapter
 */
public class TopPagerAdapter extends PagerAdapter {
    private List<DailyListBean.TopStoriesBean> mList = new ArrayList<>();
    private Context mContext;
    public TopPagerAdapter(Context context, List<DailyListBean.TopStoriesBean> mList)
    {
        this.mList = mList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_top_pager, container, false);
        ImageView ivImage = (ImageView) view.findViewById(R.id.iv_top_image);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_top_title);
        ImageLoader.load(mContext,mList.get(position).getImage(),ivImage);
        tvTitle.setText(mList.get(position).getTitle());
        final int id = mList.get(position).getId();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(mContext, ZhihuDetailActivity.class);
//                intent.putExtra(Constants.IT_ZHIHU_DETAIL_ID, id);
//                intent.putExtra(Constants.IT_ZHIHU_DETAIL_TRANSITION, true);
//                mContext.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
