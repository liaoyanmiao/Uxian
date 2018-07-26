package org.leo.uxian.presenter;

import android.content.Context;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 代理基类
 * Created by Administrator on 2018/7/24.
 */

public abstract class BasePresenter{
    private Reference<Context> mReference = null;
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
