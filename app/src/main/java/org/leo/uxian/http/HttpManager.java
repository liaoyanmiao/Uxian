package org.leo.uxian.http;

import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.orhanobut.logger.Logger;

import org.leo.uxian.exceptions.RetryWhenNetworkException;
import org.leo.uxian.http.api.BaseApi;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public enum HttpManager {
    INSTANCE;

    /**
     * 处理http请求
     *
     * @param baseApi 封装的请求数据
     */
    @SuppressWarnings("unchecked")
    public void doHttpDeal(BaseApi baseApi) {
        //手动创建一个OkHttpClient并设置超时时间缓存等设置
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(baseApi.getConnectionTime(), TimeUnit.SECONDS);
        if (baseApi.isLogger()) {
            builder.addInterceptor(getHttpLoggingInterceptor());
        }
        /*创建retrofit对象*/
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseApi.getBaseUrl())
                .build();
        /*rx处理*/
        HttpResponseObserver observer = new HttpResponseObserver(baseApi);
        Observable observable = baseApi.getObservable(retrofit)
                .retryWhen(new RetryWhenNetworkException(baseApi.getRetryCount(),
                        baseApi.getRetryDelay(),baseApi.getRetryIncreaseDelay()))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseApi);
        /*链接式对象返回*/
        SoftReference<HttpResponseListener> httpResponseListener = baseApi.getListener();
        if (httpResponseListener != null && httpResponseListener.get() != null) {
            httpResponseListener.get().onNext(observable);
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
