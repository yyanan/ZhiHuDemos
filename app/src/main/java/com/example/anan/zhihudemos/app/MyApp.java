package com.example.anan.zhihudemos.app;

import android.app.Application;

import com.example.anan.zhihudemos.http.HttpManager;
import com.example.anan.zhihudemos.http.server.WeChatApis;
import com.example.anan.zhihudemos.http.server.ZhihuApis;
import com.example.anan.zhihudemos.utils.ImplPreferencesHelper;

public class MyApp extends Application {

    private static MyApp myApp;
    private static ImplPreferencesHelper implPreferencesHelper;
    private static ZhihuApis zhiHuServer;
    private static WeChatApis weChatApis;
    @Override
    public void onCreate() {
        super.onCreate();
        myApp=this;
    }

    public static MyApp getInstance() {
        return myApp;
    }
    public static ImplPreferencesHelper getImplPreferencesHelper(){
        if (implPreferencesHelper == null) {
            synchronized (ImplPreferencesHelper.class){
                if (implPreferencesHelper == null) {
                    implPreferencesHelper=new ImplPreferencesHelper();
                }
            }
        }
        return implPreferencesHelper;
    }

    /**
     * 获取知乎网络请求Api
     * @return
     */
    public static ZhihuApis getZhihuServer(){
        if (zhiHuServer == null) {
            synchronized (ZhihuApis.class){
                if (zhiHuServer == null) {
                    zhiHuServer= HttpManager.getInstance().getApiserver(ZhihuApis.HOST,ZhihuApis.class);
                }
            }
        }
        return zhiHuServer;
    }
    /*public static WeChatApis getWeiXServer(){
        if (weChatApis == null) {
            synchronized (WeChatApis.class){
                if (weChatApis == null) {
                    weChatApis= HttpManager.getInstance().getApiserver(WeChatApis.HOST,WeChatApis.class);
                }
            }
        }
        return weChatApis;
    }*/
}
