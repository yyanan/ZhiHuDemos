package com.example.anan.zhihudemos.utils;

import com.example.anan.zhihudemos.beans.DailyListBean;
import com.example.anan.zhihudemos.beans.WXItemBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目名：xiaoshixun
 * 包名：  notice.liangxq.com.mymvp.utils
 * 文件名：RxUtils
 * 创建者：liangxq
 * 创建时间：2018/12/12  16:12
 * 描述：TODO
 */
public class RxUtils {

    /**
     * 统一线程处理
     */
    public static <T> ObservableTransformer<T, T> rxObserableSchedulerHelper() {
        return  new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
