package org.leo.uxian.http;

import org.leo.uxian.http.api.BaseApi;

import java.lang.ref.SoftReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/7/26.
 */

public class HttpResponseObserver<T> implements Observer<T> {
    /* 软引用回调接口*/
    private SoftReference<HttpResponseListener> reference;
    /*请求数据*/
    private BaseApi api;

    @SuppressWarnings("unchecked")
    public HttpResponseObserver(BaseApi api) {
        this.api = api;
        this.reference = api.getListener();
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog...
     */
    @Override
    public void onSubscribe(Disposable d) {

    }

    /**
     * 完成，隐藏ProgressDialog...
     */
    @Override
    public void onComplete() {

    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (reference.get() != null) {
            reference.get().onError(e);
        }
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @SuppressWarnings("unchecked")
    @Override
    public void onNext(T t) {
        if (reference.get() != null) {
            reference.get().onSuccess(t);
        }
    }
}
