package com.example.oujunlong.wanandroid2.presenter;

import com.example.oujunlong.wanandroid2.base.BasePresenter;
import com.example.oujunlong.wanandroid2.bean.ZhiShiDateBean;
import com.example.oujunlong.wanandroid2.http.ResultCallBack;
import com.example.oujunlong.wanandroid2.model.ZhiShiDateModel;
import com.example.oujunlong.wanandroid2.view.ZhishiDateView;

/**
 * creation time 2019/5/6
 * author oujunlong
 */
public class ZhiShiDatePresenter extends BasePresenter<ZhishiDateView> {

    private ZhiShiDateModel zhiShiDateModel;

    @Override
    protected void initModel() {
        zhiShiDateModel = new ZhiShiDateModel();
    }
    public void getData(String id,String i){
        zhiShiDateModel.getData(new ResultCallBack<ZhiShiDateBean>() {
            @Override
            public void onSuccess(ZhiShiDateBean bean) {
                mView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {

            }
        },id,i);
    }
}
