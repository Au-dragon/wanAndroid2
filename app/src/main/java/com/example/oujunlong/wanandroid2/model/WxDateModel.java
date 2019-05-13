package com.example.oujunlong.wanandroid2.model;

import com.example.oujunlong.wanandroid2.base.BaseModel;
import com.example.oujunlong.wanandroid2.bean.WxDateBean;
import com.example.oujunlong.wanandroid2.http.ApiService;
import com.example.oujunlong.wanandroid2.http.BaseObserver;
import com.example.oujunlong.wanandroid2.http.HttpUtils;
import com.example.oujunlong.wanandroid2.http.ResultCallBack;
import com.example.oujunlong.wanandroid2.http.RxUtils;


import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class WxDateModel extends BaseModel {

    public void getDatas(final ResultCallBack<WxDateBean> callBack, String s, String id) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.URL, ApiService.class);
        Observable<WxDateBean> wxDateBean = apiserver.getWxDateBean("wxarticle/list/" + id + "/"+s+"/json");
        wxDateBean.compose(RxUtils.<WxDateBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WxDateBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WxDateBean wxDateBean) {
                        callBack.onSuccess(wxDateBean);
                    }
                });
    }
}
