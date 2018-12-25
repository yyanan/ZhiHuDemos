package com.example.anan.zhihudemos.http.zhuhu;

import com.example.anan.zhihudemos.RequsetApi;
import com.example.anan.zhihudemos.http.HttpManager;
import com.example.anan.zhihudemos.http.server.ZhihuApis;

import io.reactivex.Observable;

public class ZhuhuManager {
    private static ZhihuApis zhihuApis;

    public static ZhihuApis getZhihuApis() {
        if (zhihuApis == null) {
            synchronized (ZhihuApis.class){
                if (zhihuApis == null) {
                    zhihuApis= HttpManager.getInstance().getApiserver(ZhihuApis.HOST,ZhihuApis.class);
                }
            }
        }
        return zhihuApis;
    }
    public Observable<String> getDailyList(RequsetApi requsetApi){
        switch (requsetApi){
            case ONE:
                zhihuApis.getDailyList();
                break;
            case TWO:
                zhihuApis.getThemeList();
                break;
            case THREE:
                zhihuApis.getSectionList();
                break;
            case FOUR:
                zhihuApis.getHotList();
                break;
        }
        return null;
    }
}
