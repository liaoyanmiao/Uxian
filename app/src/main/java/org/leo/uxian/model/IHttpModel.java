package org.leo.uxian.model;

import org.leo.uxian.entity.Translation;
import org.leo.uxian.http.HttpResponseListener;

/**
 * Created by Administrator on 2018/7/26.
 */

public interface IHttpModel {
    void requestTest(HttpResponseListener<Translation> listener);
}
