package org.leo.uxian.http.api;

import org.leo.uxian.http.HttpOnNextListener;

import java.lang.ref.SoftReference;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/7/25.
 */

public abstract class BaseApi<T> implements Function<T,T>{
    /*基础url*/
    private String baseUrl;
    /*回调*/
    private SoftReference<HttpOnNextListener> listener;
    /**
     * 设置参数
     * @param retrofit obj
     * @return
     */
    public abstract Observable getObservable(Retrofit retrofit);
    /* 失败后retry次数*/
    private int retryCount = 1;
    /*失败后retry延迟*/
    private long retryDelay = 100;
    /*失败后retry叠加延迟*/
    private long retryIncreaseDelay = 10;
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public SoftReference<HttpOnNextListener> getListener() {
        return listener;
    }

    public void setListener(SoftReference<HttpOnNextListener> listener) {
        this.listener = listener;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public long getRetryDelay() {
        return retryDelay;
    }

    public void setRetryDelay(long retryDelay) {
        this.retryDelay = retryDelay;
    }

    public long getRetryIncreaseDelay() {
        return retryIncreaseDelay;
    }

    public void setRetryIncreaseDelay(long retryIncreaseDelay) {
        this.retryIncreaseDelay = retryIncreaseDelay;
    }

    @Override
    public T apply(@NonNull T t) throws Exception {
        return t;
    }
}
