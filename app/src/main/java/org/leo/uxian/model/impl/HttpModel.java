package org.leo.uxian.model.impl;

import org.leo.uxian.entity.PropagandaQueryPageEntity;
import org.leo.uxian.http.HttpManager;
import org.leo.uxian.http.HttpResponseListener;
import org.leo.uxian.http.api.PropagandaQueryPageApi;
import org.leo.uxian.model.IHttpModel;

import java.util.List;

/**
 * Created by Administrator on 2018/7/26.
 */

public class HttpModel implements IHttpModel{


    @Override
    public void executePropagandaQueryPage(HttpResponseListener<List<PropagandaQueryPageEntity>> listener) {
        PropagandaQueryPageApi propagandaQueryPageApi = new PropagandaQueryPageApi(listener);
        HttpManager.INSTANCE.doHttpDeal(propagandaQueryPageApi);
    }
}
