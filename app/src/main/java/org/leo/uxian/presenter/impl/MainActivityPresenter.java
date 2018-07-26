package org.leo.uxian.presenter.impl;

import org.leo.uxian.model.IHttpModel;
import org.leo.uxian.model.impl.HttpModel;
import org.leo.uxian.presenter.BasePresenter;
import org.leo.uxian.presenter.IMainActivityPresenter;

/**
 * Created by Administrator on 2018/7/24.
 */

public class MainActivityPresenter extends BasePresenter implements IMainActivityPresenter{

    private IHttpModel iHttpModel;

    public MainActivityPresenter() {
        iHttpModel = new HttpModel();
    }

    @Override
    public void queryPage() {

    }
}
