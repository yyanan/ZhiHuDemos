package com.example.anan.zhihudemos.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anan.zhihudemos.R;
import com.example.anan.zhihudemos.base.SimpleFragment;
import com.example.anan.zhihudemos.fragment.Zhihudaily.Daily_New;
import com.example.anan.zhihudemos.fragment.Zhihudaily.HotFragment;
import com.example.anan.zhihudemos.fragment.Zhihudaily.SpecialFragment;
import com.example.anan.zhihudemos.fragment.Zhihudaily.TheemeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuMainFragment extends SimpleFragment {

    @BindView(R.id.tab_zhihu_main)
    TabLayout tabZhihuMain;
    @BindView(R.id.vp_zhihu_main)
    ViewPager vpZhihuMain;
    String[] tabTitle=new String[]{"日报","主题","专栏","热门"};
    List<Fragment> fragments=new ArrayList<Fragment>();
    public ZhihuMainFragment() {
        // Required empty public constructor
    }
    @Override
    public int createLayoutId() {
        return R.layout.fragment_zhihu_main;
    }

    @Override
    protected void initEventAndData() {
        fragments.add(new Daily_New());
        fragments.add(new TheemeFragment());
        fragments.add(new SpecialFragment());
        fragments.add(new HotFragment());
        vpZhihuMain.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        //TabLayout配合ViewPager有时会出现不显示Tab文字的Bug,需要按如下顺序
        tabZhihuMain.addTab(tabZhihuMain.newTab().setText(tabTitle[0]));
        tabZhihuMain.addTab(tabZhihuMain.newTab().setText(tabTitle[1]));
        tabZhihuMain.addTab(tabZhihuMain.newTab().setText(tabTitle[2]));
        tabZhihuMain.addTab(tabZhihuMain.newTab().setText(tabTitle[3]));
        tabZhihuMain.setupWithViewPager(vpZhihuMain);
        tabZhihuMain.getTabAt(0).setText(tabTitle[0]);
        tabZhihuMain.getTabAt(1).setText(tabTitle[1]);
        tabZhihuMain.getTabAt(2).setText(tabTitle[2]);
        tabZhihuMain.getTabAt(3).setText(tabTitle[3]);
    }
}
