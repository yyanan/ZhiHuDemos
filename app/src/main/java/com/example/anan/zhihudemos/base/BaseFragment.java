package com.example.anan.zhihudemos.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment<V, P extends BasePresenter<V>> extends SimpleFragment {

    public P presenter;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        EventBus.getDefault().register(this);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView((V) this);
        }
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
//        EventBus.getDefault().unregister(this);
    }

    //创建指定的P层对象
    public abstract P createPresenter();
}
