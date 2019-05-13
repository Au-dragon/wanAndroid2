package com.example.oujunlong.wanandroid2.presenter;

import com.example.oujunlong.wanandroid2.base.BasePresenter;
import com.example.oujunlong.wanandroid2.bean.ProjectTabBean;
import com.example.oujunlong.wanandroid2.http.ResultCallBack;
import com.example.oujunlong.wanandroid2.model.ProjectModel;
import com.example.oujunlong.wanandroid2.view.ProjectView;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class ProjectPresenter extends BasePresenter<ProjectView> {

    private ProjectModel projectModel;

    @Override
    protected void initModel() {
        projectModel = new ProjectModel();
    }
    public void getData(){
        projectModel.getTabData(new ResultCallBack<ProjectTabBean>() {
            @Override
            public void onSuccess(ProjectTabBean bean) {
                mView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
