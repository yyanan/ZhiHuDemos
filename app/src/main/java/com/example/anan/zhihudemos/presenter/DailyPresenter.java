package com.example.anan.zhihudemos.presenter;

import com.example.anan.zhihudemos.base.IBasePresenter;
import com.example.anan.zhihudemos.beans.DailyBeforeListBean;
import com.example.anan.zhihudemos.beans.DailyListBean;
import com.example.anan.zhihudemos.beans.HotListBean;
import com.example.anan.zhihudemos.beans.SectionListBean;
import com.example.anan.zhihudemos.module.DailyModule;
import com.example.anan.zhihudemos.utils.DateUtil;
import com.example.anan.zhihudemos.view.zhihu.DailyView;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class DailyPresenter<V extends DailyView>extends IBasePresenter<V> implements DailyModule.DailyListFinish {

    private DailyModule dailyModule=new DailyModule();
    public void getDailyData(){
        if(mView!=null){
            dailyModule.getDailyData(this);
        }
    }
    public void getSection(){
        if(mView!=null){
            dailyModule.getSectionListBean(this);
        }
    }
    public void getHot(){
        if(mView!=null){
            dailyModule.getHotList(this);
        }
    }
    public void getBeforeDailyData(CalendarDay calendarDay){
        Observable.just(calendarDay)
                .map(new Function<CalendarDay, String>() {
                    @Override
                    public String apply(CalendarDay calendarDay) throws Exception {StringBuilder date = new StringBuilder();
                        String year = String.valueOf(calendarDay.getYear());
                        String month = String.valueOf(calendarDay.getMonth() + 1);
                        String day = String.valueOf(calendarDay.getDay() + 1);
                        if(month.length() < 2) {
                            month = "0" + month;
                        }
                        if(day.length() < 2) {
                            day = "0" + day;
                        }
                        return date.append(year).append(month).append(day).toString();
                    }
                }).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                if(s.equals(DateUtil.getTomorrowDate())) {
                    getDailyData();
                    return false;
                }
                return true;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                dailyModule.getBeforeData(s,DailyPresenter.this);
            }
        });
    }

    @Override
    public void setContent(DailyListBean info) {
        if (mView != null) {
            mView.showContent(info);
        }
    }

    @Override
    public void setMoreContent(DailyBeforeListBean dailyBeforeListBean) {
        if (mView != null) {
            mView.showMoreContent(dailyBeforeListBean);
        }
    }

    @Override
    public void setSectionListBean(SectionListBean sectionListBean) {
        if (mView != null) {
            mView.showSectionListBean(sectionListBean);
        }
    }

    @Override
    public void setHotList(HotListBean hotList) {
        if(mView!=null){
            mView.showHotList(hotList);
        }
    }

    @Override
    public void setHideProgressBar() {
        if (mView != null) {
            mView.hideProgressBar();
        }
    }

    @Override
    public void setShowProgressBar() {
        if (mView != null) {
            mView.showProgressBar();
        }
    }
}
