package com.example.oujunlong.wanandroid2.presenter;

import com.example.oujunlong.wanandroid2.base.BasePresenter;
import com.example.oujunlong.wanandroid2.bean.WxBean;
import com.example.oujunlong.wanandroid2.http.ResultCallBack;
import com.example.oujunlong.wanandroid2.model.WxModel;
import com.example.oujunlong.wanandroid2.view.WxView;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class WxPresenter extends BasePresenter<WxView> {

    private WxModel wxModel;

    @Override
    protected void initModel() {
        wxModel = new WxModel();
    }
    public void getDatas(){
        wxModel.getDatas(new ResultCallBack<WxBean>() {
            @Override
            public void onSuccess(WxBean bean) {
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
