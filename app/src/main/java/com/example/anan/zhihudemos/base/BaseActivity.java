package com.example.anan.zhihudemos.base;

/**
 * 项目名：xiaoshixun
 * 包名：  notice.liangxq.com.mymvp
 * 文件名：BaseActivity
 * 创建者：liangxq
 * 创建时间：2018/12/11  20:25
 * 描述：TODO
 */
public abstract class BaseActivity<V, P extends BasePresenter<V>> extends SimpleActivity {

    public P mPresenter;

    @Override
    public void onViewCreated() {
        mPresenter=createPresenter();
        if (mPresenter == null) {
            mPresenter.attachView((V) this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    //创建指定的P层对象
    public abstract P createPresenter();
}
