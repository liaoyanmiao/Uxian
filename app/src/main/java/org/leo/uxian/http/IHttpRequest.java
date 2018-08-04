package org.leo.uxian.http;

import org.leo.uxian.entity.BaseResultEntity;
import org.leo.uxian.entity.PropagandaQueryPageEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 服务器请求接口
 * Created by Administrator on 2018/7/25.
 */

public interface IHttpRequest {
    /**
     * 宣传页面查询列表
     */
    @GET("propaganda-proivder-server/propaganda/queryPage")
    Observable<BaseResultEntity<List<PropagandaQueryPageEntity>>> propagandaQueryPage();
}
