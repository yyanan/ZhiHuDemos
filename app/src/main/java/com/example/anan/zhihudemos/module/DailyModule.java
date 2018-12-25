package com.example.anan.zhihudemos.module;

import com.example.anan.zhihudemos.app.MyApp;
import com.example.anan.zhihudemos.base.BaseObserver;
import com.example.anan.zhihudemos.base.HttpFinishCallBack;
import com.example.anan.zhihudemos.beans.DailyBeforeListBean;
import com.example.anan.zhihudemos.beans.DailyListBean;
import com.example.anan.zhihudemos.beans.HotListBean;
import com.example.anan.zhihudemos.beans.SectionListBean;
import com.example.anan.zhihudemos.utils.RxUtils;

public class DailyModule {
    public DailyModule() {

    }


    public interface DailyListFinish extends HttpFinishCallBack {

        void setContent(DailyListBean info);

        void setMoreContent(DailyBeforeListBean dailyBeforeListBean);
        void setSectionListBean(SectionListBean sectionListBean);
        void setHotList(HotListBean hotList);
    }

    public void getDailyData(final DailyListFinish dailyListFinish) {
        dailyListFinish.setShowProgressBar();
        MyApp.getZhihuServer().getDailyList().compose(RxUtils.<DailyListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DailyListBean>(dailyListFinish) {
                    @Override
                    public void onNext(DailyListBean value) {
                        dailyListFinish.setContent(value);
                    }
                });

    }


    public void getBeforeData(String date, final DailyListFinish dailyListFinish) {
        dailyListFinish.setShowProgressBar();
        MyApp.getZhihuServer().getDailyBeforeList(date).compose(RxUtils.<DailyBeforeListBean>rxObserableSchedulerHelper()).subscribe(new BaseObserver<DailyBeforeListBean>(dailyListFinish) {
            @Override
            public void onNext(DailyBeforeListBean value) {
                dailyListFinish.setMoreContent(value);
            }
        });
    }

    public void getSectionListBean(final DailyListFinish dailyListFinish){
        dailyListFinish.setShowProgressBar();
        MyApp.getZhihuServer().getSectionList().compose(RxUtils.<SectionListBean>rxObserableSchedulerHelper()).subscribe(new BaseObserver<SectionListBean>(dailyListFinish) {
            @Override
            public void onNext(SectionListBean value) {
                dailyListFinish.setSectionListBean(value);
            }
        });
    }public void getHotList(final DailyListFinish dailyListFinish){
        dailyListFinish.setShowProgressBar();
        MyApp.getZhihuServer().getHotList().compose(RxUtils.<HotListBean>rxObserableSchedulerHelper()).subscribe(new BaseObserver<HotListBean>(dailyListFinish) {
            @Override
            public void onNext(HotListBean value) {
                dailyListFinish.setHotList(value);
            }
        });
    }

    void startInterval() {

    }


    void stopInterval() {

    }


    void insertReadToDB(int id) {

    }
}
