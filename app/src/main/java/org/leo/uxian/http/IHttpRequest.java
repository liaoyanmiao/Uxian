package org.leo.uxian.http;

import org.leo.uxian.entity.BaseResultEntity;
import org.leo.uxian.entity.Translation;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 服务器请求接口
 * Created by Administrator on 2018/7/25.
 */

public interface IHttpRequest {
    /**
     * 宣传列表
     */
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<BaseResultEntity<Translation>> test();
}
