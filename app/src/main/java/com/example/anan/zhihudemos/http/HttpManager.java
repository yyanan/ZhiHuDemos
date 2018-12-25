package com.example.anan.zhihudemos.http;

import com.example.anan.zhihudemos.app.Constants;
import com.example.anan.zhihudemos.utils.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static HttpManager httpManager;
    private HttpManager(){

    }
    public static HttpManager getInstance(){
        if (httpManager == null) {
            synchronized (HttpManager.class){
                if (httpManager == null) {
                    httpManager=new HttpManager();
                }
            }
        }
        return httpManager;
    }
    private Retrofit createRetrofit(String url){
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public OkHttpClient getOkHttpClient() {
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        return new OkHttpClient.Builder().
                cache(cache)
                .addInterceptor(new MyCacheinterceptor())
                .addNetworkInterceptor(new MyCacheinterceptor())
                //设置缓存时间
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                //设置错误重连
                .retryOnConnectionFailure(true).build();
    }
    private class MyCacheinterceptor implements Interceptor {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //这里就是说判读我们的网络条件，要是有网络的话我么就直接获取网络上面的数据，要是没有网络的话我么就去缓存里面取数据
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();

                }
                Response originalResponse = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    return originalResponse.newBuilder()
                            // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public ,max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 7;
                    return originalResponse.newBuilder()
                            // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .removeHeader("Pragma")
                            //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }

            }
        }
    /**
     *
     * @param baseUrl 基本URL
     * @param tClass  各个接口
     * @param <T>     各个接口对象
     * @return
     */
    public <S> S getApiserver(String baseUrl,Class<S>tClass) {
        return createRetrofit(baseUrl).create(tClass);
    }
}
