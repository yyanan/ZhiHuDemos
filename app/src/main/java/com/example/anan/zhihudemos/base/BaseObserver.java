package com.example.anan.zhihudemos.base;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 项目名：xiaoshixun
 * 包名：  notice.liangxq.com.mymvp.base
 * 文件名：BaseObserver
 * 创建者：liangxq
 * 创建时间：2018/12/12  22:37
 * 描述：TODO
 */
public abstract class BaseObserver<T> implements Observer<T> {
    private HttpFinishCallBack httpFinishCallBack;

    public BaseObserver(HttpFinishCallBack httpFinishCallBack) {
        this.httpFinishCallBack = httpFinishCallBack;
    }

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void onSubscribe(Disposable d) {
        mCompositeDisposable.add(d);
    }


    @Override
    public void onError(Throwable e) {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
        if(httpFinishCallBack!=null){
            httpFinishCallBack.setHideProgressBar();
        }
    }

    @Override
    public void onComplete() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
        if(httpFinishCallBack!=null){
            httpFinishCallBack.setHideProgressBar();
        }
    }
}
