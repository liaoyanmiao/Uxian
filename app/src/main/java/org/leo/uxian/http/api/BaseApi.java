package org.leo.uxian.http.api;

import com.orhanobut.logger.Logger;

import org.leo.uxian.entity.BaseResultEntity;
import org.leo.uxian.http.HttpResponseListener;

import java.lang.ref.SoftReference;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;

public abstract class BaseApi<T> implements Function<BaseResultEntity<T>,T>{
    /*回调*/
    private SoftReference<HttpResponseListener> listener;
    /*基础url*/
    private String baseUrl = "http://120.79.143.68:18080/";
    /*超时时间-默认6秒*/
    private int connectionTime = 6;
    /* 失败后retry次数*/
    private int retryCount = 1;
    /*失败后retry延迟*/
    private long retryDelay = 100;
    /*失败后retry叠加延迟*/
    private long retryIncreaseDelay = 10;
    /*是否打印响应日志*/
    private boolean isLogger;
    public BaseApi(HttpResponseListener listener) {
        setListener(listener);
    }

    public SoftReference<HttpResponseListener> getListener() {
        return listener;
    }

    public void setListener(HttpResponseListener listener) {
        this.listener = new SoftReference<>(listener);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
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

    public boolean isLogger() {
        return isLogger;
    }

    public void setLogger(boolean logger) {
        isLogger = logger;
    }

    /**
     * 设置参数
     *
     * @param retrofit
     * @return
     */
    public abstract Observable getObservable(Retrofit retrofit);
    @Override
    public T apply(BaseResultEntity<T> httpResult) {
        if (httpResult.getCode() != 200) {
            Logger.d(httpResult.getMsg());
        }
        return httpResult.getData();
    }
}
