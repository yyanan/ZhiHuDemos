package com.example.anan.zhihudemos.view.zhihu;

import com.example.anan.zhihudemos.beans.DailyBeforeListBean;
import com.example.anan.zhihudemos.beans.DailyListBean;
import com.example.anan.zhihudemos.beans.HotListBean;
import com.example.anan.zhihudemos.beans.SectionListBean;
import com.example.anan.zhihudemos.view.BaseView;

public interface DailyView extends BaseView {

    void showContent(DailyListBean info);
    void showMoreContent(DailyBeforeListBean  dailyBeforeListBean);
    void showSectionListBean(SectionListBean sectionListBean);
    void showHotList(HotListBean hotListBean);
}
