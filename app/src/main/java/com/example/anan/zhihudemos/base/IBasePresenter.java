package com.example.anan.zhihudemos.base;

import com.example.anan.zhihudemos.view.BaseView;

import java.lang.ref.WeakReference;


/**
 * 项目名：xiaoshixun
 * 包名：  notice.liangxq.com.mymvp.base
 * 文件名：IBasePresenter
 * 创建者：liangxq
 * 创建时间：2018/12/11  20:36
 * 描述：TODO
 */
public class IBasePresenter<V extends BaseView> implements BasePresenter<V> {

    private WeakReference<V> weakReference;

    public V mView;


    @Override
    public void attachView(V v) {
        weakReference = new WeakReference<V>(v);
        mView = weakReference.get();
    }

    @Override
    public void detachView() {
        if (weakReference != null) {
            weakReference.clear();
            mView = null;
        }
    }
}
