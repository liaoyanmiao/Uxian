package org.leo.uxian.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;

import org.leo.uxian.R;
import org.leo.uxian.presenter.BasePresenter;
import org.leo.uxian.widgets.CustomDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    protected AppCompatActivity context;
    protected View mRootView;
    protected ImmersionBar mImmersionBar;
    private Unbinder unbinder;
    protected T mPresenter;
    private CustomDialog dialog;
    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完onViewCreated方法后即为true
     */
    protected boolean mIsPrepare;
    /**
     * 是否加载完成
     * 当执行完onViewCreated方法后即为true
     */
    protected boolean mIsImmersion;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (AppCompatActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, mRootView);
        //创建代理，用于统一数据访问
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.onAttach(context);
        }
        dialog = new CustomDialog(context, R.style.CustomDialog);
        if (isLazyLoad()) {
            mIsPrepare = true;
            mIsImmersion = true;
            onLazyLoad();
        } else {
            initData();
            if (isImmersionBarEnabled())
                initImmersionBar();
        }
        initView();
        setListener();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }

    /**
     * 是否懒加载
     * @return the boolean
     */
    protected boolean isLazyLoad() {
        return true;
    }
    private void onLazyLoad() {
        if (mIsVisible && mIsPrepare) {
            mIsPrepare = false;
            initData();
        }
        if (mIsVisible && mIsImmersion && isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }
    /**
     * 初始化数据
     */
    protected void initData() {
        //do something here
    }
    /**
     * 是否在Fragment使用沉浸式
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }
    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }
    /**
     * 显示加载对话框
     */
    protected void showProgressDialog(){
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }
    /**
     * 关闭加载对话框
     */
    protected void dismiss(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    /**
     * view与数据绑定
     */
    protected void initView() {
        //do something
    }
    /**
     * 设置监听
     */
    protected void setListener() {
        //do something
    }
    /**
     * 用户不可见执行
     */
    protected void onInvisible() {
        //do something
    }
    /**
     * 用户可见时执行的操作
     */
    protected void onVisible() {
        onLazyLoad();
    }
    protected abstract int setLayoutId();
    protected abstract T createPresenter();
}
