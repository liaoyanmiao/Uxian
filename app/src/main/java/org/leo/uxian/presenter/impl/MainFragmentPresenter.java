package org.leo.uxian.presenter.impl;

import com.orhanobut.logger.Logger;

import org.leo.uxian.entity.PropagandaQueryPageEntity;
import org.leo.uxian.fragment.MainFragment;
import org.leo.uxian.http.HttpResponseListener;
import org.leo.uxian.model.IHttpModel;
import org.leo.uxian.model.impl.HttpModel;
import org.leo.uxian.presenter.BasePresenter;
import org.leo.uxian.presenter.IMainFragmentPresenter;
import org.leo.uxian.view.IMainFragment;

import java.util.List;

public class MainFragmentPresenter extends BasePresenter<IMainFragment> implements IMainFragmentPresenter{
    private IHttpModel iHttpModel;
    public MainFragmentPresenter(MainFragment view) {
        super(view);
        iHttpModel = new HttpModel();
    }

    @Override
    public void invokePropagandaQueryPage() {
        iHttpModel.executePropagandaQueryPage(new HttpResponseListener<List<PropagandaQueryPageEntity>>() {
            @Override
            public void onSuccess(List<PropagandaQueryPageEntity> s) {
                view.showPropagandaQueryPage();
            }

            @Override
            public void onError(Throwable e) {
                Logger.d(e.toString());
            }
        });
    }
}
