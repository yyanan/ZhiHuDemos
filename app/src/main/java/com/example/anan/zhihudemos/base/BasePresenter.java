package com.example.anan.zhihudemos.base;

/**
 * 项目名：xiaoshixun
 * 包名：  notice.liangxq.com.mymvp.base
 * 文件名：BasePresenter
 * 创建者：liangxq
 * 创建时间：2018/12/11  20:26
 * 描述：TODO
 */
public interface BasePresenter<V> {

    void attachView(V v);

    void detachView();


}
