package org.leo.uxian.presenter.impl;

import org.leo.uxian.entity.Translation;
import org.leo.uxian.http.HttpResponseListener;
import org.leo.uxian.model.IHttpModel;
import org.leo.uxian.model.impl.HttpModel;
import org.leo.uxian.presenter.BasePresenter;
import org.leo.uxian.presenter.IMainActivityPresenter;
import org.leo.uxian.view.IMainFragment;

/**
 * Created by Administrator on 2018/7/24.
 */

public class MainActivityPresenter extends BasePresenter<IMainFragment> implements IMainActivityPresenter{

    private IHttpModel iHttpModel;

    public MainActivityPresenter(IMainFragment view) {
        super(view);
        iHttpModel = new HttpModel();
    }

    @Override
    public void test() {
        iHttpModel.requestTest(new HttpResponseListener<Translation>() {
            @Override
            public void onSuccess(Translation translation) {
                translation.show();
                view.testUi();
            }
        });
    }
}
