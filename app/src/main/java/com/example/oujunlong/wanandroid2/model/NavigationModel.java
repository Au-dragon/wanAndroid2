package com.example.oujunlong.wanandroid2.model;

import com.example.oujunlong.wanandroid2.base.BaseModel;
import com.example.oujunlong.wanandroid2.bean.NavigationBean;
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
public class NavigationModel extends BaseModel {

    public void getData(final ResultCallBack<NavigationBean> callBack){
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.URL, ApiService.class);
        Observable<NavigationBean> naviagtionData = apiserver.getNaviagtionData();
        naviagtionData.compose(RxUtils.<NavigationBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<NavigationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(NavigationBean navigationBean) {
                        callBack.onSuccess(navigationBean);
                    }
                });
    }
}
