package org.leo.uxian;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.leo.uxian.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    private Unbinder unbinder;
    protected AppCompatActivity context = this;
    protected ImmersionBar mImmersionBar;
    protected T mPresenter;
    private boolean isLogger = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        //绑定控件
        unbinder = ButterKnife.bind(this);
        //初始化沉浸式
        if (isImmersionBarEnabled())
            initImmersionBar();
        //创建代理，用于统一数据访问
        mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.onAttach(context);
        //初始化日志
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return isLogger;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
        if (mPresenter != null)
            mPresenter.onDetach();
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    protected abstract int setLayoutId();
    protected abstract T createPresenter();
}
