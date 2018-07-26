package org.leo.uxian.http;

/**
 * 成功回调处理
 * Created by Administrator on 2018/7/25.
 */

public abstract class HttpOnNextListener<T> {
    /**
     * 成功后回调方法
     * @param t result
     */
    public abstract void onNext(T t);
    /**
     * 失败或者错误方法
     * 主动调用，更加灵活
     * @param e error
     */
    public void onError(Throwable e){

    }
}
