package org.leo.uxian.model.impl;

import org.leo.uxian.entity.Translation;
import org.leo.uxian.http.HttpManager;
import org.leo.uxian.http.HttpResponseListener;
import org.leo.uxian.http.api.TestApi;
import org.leo.uxian.model.IHttpModel;

/**
 * Created by Administrator on 2018/7/26.
 */

public class HttpModel implements IHttpModel{

    @Override
    public void requestTest(HttpResponseListener<Translation> listener) {
        TestApi testApi = new TestApi(listener);
        HttpManager.INSTANCE.doHttpDeal(testApi);
    }
}
