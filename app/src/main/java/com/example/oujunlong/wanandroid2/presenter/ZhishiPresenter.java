package com.example.oujunlong.wanandroid2.presenter;

import com.example.oujunlong.wanandroid2.base.BasePresenter;
import com.example.oujunlong.wanandroid2.bean.ZhishiBean;
import com.example.oujunlong.wanandroid2.http.ApiService;
import com.example.oujunlong.wanandroid2.model.ZhiShiModel;
import com.example.oujunlong.wanandroid2.view.ZhishiView;

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
public class ZhishiPresenter extends BasePresenter<ZhishiView> implements ZhiShiModel.CallBack {

    private ZhiShiModel zhiShiModel;

    @Override
    protected void initModel() {
        zhiShiModel = new ZhiShiModel();
    }
    public void getDatas(){
        zhiShiModel.getDatas(this);
    }

    @Override
    public void onSuccess(ZhishiBean zhiShiBean) {
        if(mView!=null){
            mView.onSuccess(zhiShiBean);
        }

    }
}
