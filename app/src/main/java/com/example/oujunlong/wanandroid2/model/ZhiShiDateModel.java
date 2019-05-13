package com.example.oujunlong.wanandroid2.model;

import com.example.oujunlong.wanandroid2.base.BaseModel;
import com.example.oujunlong.wanandroid2.bean.ZhiShiDateBean;
import com.example.oujunlong.wanandroid2.http.ApiService;
import com.example.oujunlong.wanandroid2.http.BaseObserver;
import com.example.oujunlong.wanandroid2.http.HttpUtils;
import com.example.oujunlong.wanandroid2.http.ResultCallBack;
import com.example.oujunlong.wanandroid2.http.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * creation time 2019/5/6
 * author oujunlong
 */
public class ZhiShiDateModel extends BaseModel {

    public void getData(final ResultCallBack<ZhiShiDateBean> callBack, String id,String i){
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.URL, ApiService.class);
        Observable<ZhiShiDateBean> zhiShiDateDate = apiserver.getZhiShiDateDate("article/list/"+i+"/json?cid=" + id);
        zhiShiDateDate.compose(RxUtils.<ZhiShiDateBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ZhiShiDateBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ZhiShiDateBean zhiShiDateBean) {
                            callBack.onSuccess(zhiShiDateBean);
                    }
                });
    }
}
