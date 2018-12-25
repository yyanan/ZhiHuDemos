package com.example.anan.zhihudemos.fragment.Zhihudaily;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anan.zhihudemos.R;
import com.example.anan.zhihudemos.adapter.HotAdapter;
import com.example.anan.zhihudemos.adapter.SpecialAdapter;
import com.example.anan.zhihudemos.base.BaseFragment;
import com.example.anan.zhihudemos.beans.DailyBeforeListBean;
import com.example.anan.zhihudemos.beans.DailyListBean;
import com.example.anan.zhihudemos.beans.HotListBean;
import com.example.anan.zhihudemos.beans.SectionListBean;
import com.example.anan.zhihudemos.presenter.DailyPresenter;
import com.example.anan.zhihudemos.view.zhihu.DailyView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment<DailyView,DailyPresenter<DailyView>> implements DailyView,XRecyclerView.LoadingListener {


    @BindView(R.id.view_main)
    XRecyclerView viewMain;
    List<HotListBean.RecentBean> mList = new ArrayList<>();
    private HotAdapter hotAdapter;

    @Override
    public int createLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initEventAndData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewMain.setLayoutManager(layoutManager);
        hotAdapter= new HotAdapter(mList,activity);
        viewMain.setAdapter(hotAdapter);
        presenter.getHot();
    }

    @Override
    public DailyPresenter<DailyView> createPresenter() {
        return new DailyPresenter<>();
    }

    @Override
    public void showContent(DailyListBean info) {

    }

    @Override
    public void showMoreContent(DailyBeforeListBean dailyBeforeListBean) {

    }

    @Override
    public void showSectionListBean(SectionListBean sectionListBean) {
//        mList.addAll(sectionListBean.getData());
    }

    @Override
    public void showHotList(HotListBean hotListBean) {
        mList.addAll(hotListBean.getRecent());
    }

    @Override
    public void hideProgressBar() {
//        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
//        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        presenter.getHot();
        viewMain.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        viewMain.loadMoreComplete();
    }
}

