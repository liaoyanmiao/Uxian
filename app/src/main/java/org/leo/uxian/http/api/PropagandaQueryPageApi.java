package org.leo.uxian.http.api;

import org.leo.uxian.http.HttpResponseListener;
import org.leo.uxian.http.IHttpRequest;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class PropagandaQueryPageApi extends BaseApi{
    public PropagandaQueryPageApi(HttpResponseListener listener) {
        super(listener);
        setLogger(false);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        IHttpRequest request = retrofit.create(IHttpRequest.class);
        return request.propagandaQueryPage();
    }
}
