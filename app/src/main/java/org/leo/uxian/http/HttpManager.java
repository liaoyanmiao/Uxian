package org.leo.uxian.http;

import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.orhanobut.logger.Logger;

import org.leo.uxian.exceptions.RetryWhenNetworkException;
import org.leo.uxian.http.api.BaseApi;

import java.lang.ref.SoftReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/7/25.
 */

public enum HttpManager {
    INSTANCE;
    /**
     * 处理http请求
     * @param baseApi 封装的请求数据
     */
    @SuppressWarnings("unchecked")
    public void doHttpDeal(BaseApi baseApi) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseApi.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        /*rx处理*/
        HttpObserver observer = new HttpObserver(baseApi);
        Observable observable = baseApi.getObservable(retrofit)
                /*失败后的retry配置*/
                .retryWhen(new RetryWhenNetworkException(baseApi.getRetryCount(),
                        baseApi.getRetryDelay(),baseApi.getRetryIncreaseDelay()))
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseApi);
        /*链接式对象返回*/
        SoftReference<HttpOnNextListener> httpOnNextListener = baseApi.getListener();
        if (httpOnNextListener != null && httpOnNextListener.get() != null) {
            httpOnNextListener.get().onNext(observable);
        }
        /*数据回调*/
        observable.subscribe(observer);
    }
    /**
     * 日志输出
     * 自行判定是否添加
     * @return
     */
    private HttpLoggingInterceptor getHttpLoggingInterceptor(){
        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Logger.d("Retrofit====Message:"+message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }
}
