package com.example.oujunlong.wanandroid2.model;

import com.example.oujunlong.wanandroid2.base.BaseModel;
import com.example.oujunlong.wanandroid2.bean.WxBean;
import com.example.oujunlong.wanandroid2.http.ApiService;
import com.example.oujunlong.wanandroid2.http.BaseObserver;
import com.example.oujunlong.wanandroid2.http.HttpUtils;
import com.example.oujunlong.wanandroid2.http.ResultCallBack;
import com.example.oujunlong.wanandroid2.http.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class WxModel extends BaseModel {
        public void getDatas(final ResultCallBack<WxBean> callBack){
            ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.URL, ApiService.class);
            Observable<WxBean> wxBeanData = apiserver.getWxBeanData();
            wxBeanData.compose(RxUtils.<WxBean>rxObserableSchedulerHelper())
                    .subscribe(new BaseObserver<WxBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mCompositeDisposable.add(d);
                        }

                        @Override
                        public void onNext(WxBean wxBean) {
                            callBack.onSuccess(wxBean);
                        }
                    });
        }
}
