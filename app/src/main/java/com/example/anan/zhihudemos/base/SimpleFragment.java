package com.example.anan.zhihudemos.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名：xiaoshixun
 * 包名：  notice.liangxq.com.mymvp.base
 * 文件名：SimpleFragment
 * 创建者：liangxq
 * 创建时间：2018/12/12  18:55
 * 描述：TODO
 */
public abstract class SimpleFragment extends Fragment {

    private Unbinder bind;

    public Activity activity;
    //是否执行了懒加载
    protected boolean isInited = false;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (Activity) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(createLayoutId(), null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(this,view);
        initEventAndData();
    }

    //创建布局id
    public abstract int createLayoutId();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isInited=true;
            loadUserVisibleData();
        }
    }

    //懒加载数据,之类可重写或者不重写
    private void loadUserVisibleData() {

    }

    //初始化数据
    protected abstract void initEventAndData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(bind!=null){
            bind.unbind();
        }
    }
}
