package com.example.oujunlong.wanandroid2.presenter;

import com.example.oujunlong.wanandroid2.base.BasePresenter;
import com.example.oujunlong.wanandroid2.bean.ProjectListBean;
import com.example.oujunlong.wanandroid2.http.ResultCallBack;
import com.example.oujunlong.wanandroid2.model.ProjectDateModel;
import com.example.oujunlong.wanandroid2.view.ProjectDateView;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class ProjectDatePresenter extends BasePresenter<ProjectDateView> {


    private ProjectDateModel projectDateModel;

    @Override
    protected void initModel() {
        projectDateModel = new ProjectDateModel();
    }
    public void getData(String s, String id){
        projectDateModel.getData(new ResultCallBack<ProjectListBean>() {
            @Override
            public void onSuccess(ProjectListBean bean) {
                if(mView!=null){
                    mView.onSuccess(bean);
                }

            }

            @Override
            public void onFail(String msg) {

            }
        },id,s);
    }
}
