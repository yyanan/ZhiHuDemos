package com.example.anan.zhihudemos.fragment.Zhihudaily;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.anan.zhihudemos.R;
import com.example.anan.zhihudemos.activitys.CalendarActivity;
import com.example.anan.zhihudemos.adapter.DailyAdapter;
import com.example.anan.zhihudemos.base.BaseFragment;
import com.example.anan.zhihudemos.beans.DailyBeforeListBean;
import com.example.anan.zhihudemos.beans.DailyListBean;
import com.example.anan.zhihudemos.beans.HotListBean;
import com.example.anan.zhihudemos.beans.SectionListBean;
import com.example.anan.zhihudemos.presenter.DailyPresenter;
import com.example.anan.zhihudemos.utils.CircularAnimUtil;
import com.example.anan.zhihudemos.utils.DateUtil;
import com.example.anan.zhihudemos.view.zhihu.DailyView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView.LoadingListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Daily_New extends BaseFragment<DailyView,DailyPresenter<DailyView>> implements DailyView,LoadingListener {


    @BindView(R.id.view_main)
    XRecyclerView viewMain;
    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    List<DailyListBean.StoriesBean> mList = new ArrayList<>();
    Unbinder unbinder;
    private DailyAdapter dailyAdapter;
    //用于刷新的时候判断日期
    String currentDate;

    public Daily_New() {
        // Required empty public constructor
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_daily__new;
    }


    @Override
    protected void initEventAndData() {
        //获取当前的时间
        currentDate = DateUtil.getTomorrowDate();
        viewMain.setLayoutManager(new LinearLayoutManager(activity));
        dailyAdapter = new DailyAdapter(mList, activity);
        viewMain.setAdapter(dailyAdapter);
        viewMain.setLoadingListener(this);
        presenter.getDailyData();
    }

    @Override
    public DailyPresenter<DailyView> createPresenter() {
        return new DailyPresenter<>();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent(DailyListBean info) {
        dailyAdapter.addDailyDate(info);
    }

    @Override
    public void showMoreContent(DailyBeforeListBean dailyBeforeListBean) {
        //将返回的日期赋值给全局的日期，用于刷新数据用
        currentDate = String.valueOf(Integer.valueOf(dailyBeforeListBean.getDate()));
        dailyAdapter.addDailyBeforeDate(dailyBeforeListBean);
    }

    @Override
    public void showSectionListBean(SectionListBean sectionListBean) {

    }

    @Override
    public void showHotList(HotListBean hotListBean) {

    }

    @Override
    public void onRefresh() {
        if (currentDate.equals(DateUtil.getTomorrowDate())) {
            presenter.getDailyData();
        } else {
            int year = Integer.valueOf(currentDate.substring(0, 4));
            int month = Integer.valueOf(currentDate.substring(4, 6));
            int day = Integer.valueOf(currentDate.substring(6, 8));
            CalendarDay date = CalendarDay.from(year, month - 1, day);
            EventBus.getDefault().post(date);
        }
        viewMain.refreshComplete();

    }

    @Override
    public void onLoadMore() {
        viewMain.loadMoreComplete();
    }

    /**
     * 接收日期重新发起请求
     * @param date
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(CalendarDay date) {
        if (presenter != null) {
            presenter.getBeforeDailyData(date);
        }
    }


    @OnClick(R.id.fab_calender)
    public void onViewClicked() {
        Intent it = new Intent();
        it.setClass(activity, CalendarActivity.class);
        CircularAnimUtil.startActivity(activity, it, fabCalender, R.color.fab_bg);
    }
}
