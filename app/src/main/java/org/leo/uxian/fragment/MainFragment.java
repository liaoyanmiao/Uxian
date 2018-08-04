package org.leo.uxian.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;

import org.leo.uxian.R;
import org.leo.uxian.presenter.impl.MainActivityPresenter;
import org.leo.uxian.view.IMainFragment;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment<MainActivityPresenter> implements View.OnClickListener,IMainFragment{
    @BindView(R.id.button)
    Button button;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setListener() {
        button.setOnClickListener(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected MainActivityPresenter createPresenter() {
        return new MainActivityPresenter(this);
    }

    @Override
    public void onClick(View v) {
        mPresenter.test();
    }

    @Override
    public void testUi() {
        Logger.d("MainFragment");
    }
}
