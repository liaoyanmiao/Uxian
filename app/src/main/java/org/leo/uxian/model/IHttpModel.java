package org.leo.uxian.model;

import org.leo.uxian.entity.PropagandaQueryPageEntity;
import org.leo.uxian.http.HttpResponseListener;

import java.util.List;

/**
 * Created by Administrator on 2018/7/26.
 */

public interface IHttpModel {
    void executePropagandaQueryPage(HttpResponseListener<List<PropagandaQueryPageEntity>> listener);
}
