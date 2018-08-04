package org.leo.uxian.presenter.impl;

import org.leo.uxian.model.IHttpModel;
import org.leo.uxian.model.impl.HttpModel;
import org.leo.uxian.presenter.BasePresenter;
import org.leo.uxian.presenter.IMainActivityPresenter;
import org.leo.uxian.view.IMainActivity;

/**
 * Created by Administrator on 2018/7/24.
 */

public class MainActivityPresenter extends BasePresenter<IMainActivity> implements IMainActivityPresenter{

    private IHttpModel iHttpModel;

    public MainActivityPresenter(IMainActivity view) {
        super(view);
        iHttpModel = new HttpModel();
    }

}
