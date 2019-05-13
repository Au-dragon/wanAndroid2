package com.example.oujunlong.wanandroid2.presenter;

import com.example.oujunlong.wanandroid2.base.BasePresenter;
import com.example.oujunlong.wanandroid2.bean.WxDateBean;
import com.example.oujunlong.wanandroid2.http.ResultCallBack;
import com.example.oujunlong.wanandroid2.model.WxDateModel;
import com.example.oujunlong.wanandroid2.view.WxDateView;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class WxDatePresenter extends BasePresenter<WxDateView> {

    private WxDateModel wxDateModel;

    @Override
    protected void initModel() {
        wxDateModel = new WxDateModel();
    }
    public void getData(String s, String id){
        wxDateModel.getDatas(new ResultCallBack<WxDateBean>() {
            @Override
            public void onSuccess(WxDateBean bean) {
                            mView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {

            }
        },id,s);
    }
}
