package org.leo.uxian.presenter;

import android.content.Context;

import org.leo.uxian.view.IView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 代理基类
 * Created by Administrator on 2018/7/24.
 */

public abstract class BasePresenter<T extends IView>{
    protected Reference<Context> mReference = null;
    protected T view;

    public BasePresenter(T view) {
        this.view = view;
    }

    public void onAttach(Context context) {
        mReference = new WeakReference<>(context);
    }
    public void onDetach() {
        if(null != mReference){
            mReference.clear();
            mReference = null;
        }
    }
    public boolean isAttach(){
        return null != mReference && null != mReference.get();
    }
}
