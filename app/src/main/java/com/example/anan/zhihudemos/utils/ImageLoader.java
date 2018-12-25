package com.example.anan.zhihudemos.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.anan.zhihudemos.app.MyApp;


/**
 * Created by codeest on 2016/8/2.
 */
public class ImageLoader {
//
    public static void load(Context context, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        if (!MyApp.getImplPreferencesHelper().getNoImageState()) {
            RequestOptions requestOptions=new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.DATA);
            Glide.with(context).load(url).apply(requestOptions).into(iv);
        }
    }
//
//    public static void load(Activity activity, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
//        if(!activity.isDestroyed()) {
//            Glide.with(activity).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
//        }
//    }
//
//    public static void loadAll(Context context, String url, ImageView iv) {    //不缓存，全部从网络加载
//        Glide.with(context).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
//    }
//
//    public static void loadAll(Activity activity, String url, ImageView iv) {    //不缓存，全部从网络加载
//        if(!activity.isDestroyed()) {
//            Glide.with(activity).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
//        }
//    }
}
