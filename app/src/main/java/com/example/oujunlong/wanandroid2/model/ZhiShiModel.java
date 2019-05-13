package com.example.oujunlong.wanandroid2.model;

import com.example.oujunlong.wanandroid2.base.BaseModel;
import com.example.oujunlong.wanandroid2.bean.ZhishiBean;
import com.example.oujunlong.wanandroid2.http.ApiService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class ZhiShiModel extends BaseModel {
    public interface CallBack{
        void onSuccess(ZhishiBean zhiShiBean);
    }
    public void getDatas(final CallBack callBack){
        Retrofit build = new Retrofit.Builder().baseUrl(ApiService.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        final Observable<ZhishiBean> zhiShiBean = apiService.getZhiShiBean();
        zhiShiBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhishiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ZhishiBean zhishiBean) {
                            callBack.onSuccess(zhishiBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
