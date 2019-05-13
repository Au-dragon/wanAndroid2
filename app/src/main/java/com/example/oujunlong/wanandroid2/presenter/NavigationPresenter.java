package com.example.oujunlong.wanandroid2.presenter;

import com.example.oujunlong.wanandroid2.base.BasePresenter;
import com.example.oujunlong.wanandroid2.bean.NavigationBean;
import com.example.oujunlong.wanandroid2.http.ResultCallBack;
import com.example.oujunlong.wanandroid2.model.NavigationModel;
import com.example.oujunlong.wanandroid2.view.NavigationView;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class NavigationPresenter extends BasePresenter<NavigationView> {

    private NavigationModel navigationModel;

    @Override
    protected void initModel() {
        navigationModel = new NavigationModel();
    }
    public void getDatas(){
        navigationModel.getData(new ResultCallBack<NavigationBean>() {
            @Override
            public void onSuccess(NavigationBean bean) {
                if(mView!=null){
                    mView.onSuccess(bean);
                }

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
