package org.leo.uxian.http;

import org.leo.uxian.http.api.BaseApi;

import java.lang.ref.SoftReference;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/7/25.
 */

public class HttpObserver<T> implements Observer<T> {
    /*软引用回调接口*/
    private SoftReference<HttpOnNextListener> mSubscriberOnNextListener;
    /*请求数据*/
    private BaseApi api;
    @SuppressWarnings("unchecked")
    public HttpObserver(BaseApi api) {
        this.api = api;
        this.mSubscriberOnNextListener = api.getListener();
    }

    /**
     * 订阅开始时调用
     * @param d Disposable
     */

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     * @param t 创建Subscriber时的泛型类型
     */
    @SuppressWarnings("unchecked")
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onNext(t);
        }
    }

    /**
     * 对错误进行统一处理
     * @param t Throwable
     */
    @Override
    public void onError(Throwable t) {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onError(t);
        }
    }

    @Override
    public void onComplete() {

    }
}
