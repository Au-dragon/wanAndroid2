package com.example.oujunlong.wanandroid2.model;

import com.example.oujunlong.wanandroid2.base.BaseModel;
import com.example.oujunlong.wanandroid2.bean.ProjectListBean;
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
public class ProjectDateModel extends BaseModel {

    public void getData(final ResultCallBack<ProjectListBean> callBack, String s, String id){
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.URL, ApiService.class);
        Observable<ProjectListBean> projectListBean = apiserver.getProjectListBean("project/list/"+s+"/json?cid=" + id);
        projectListBean.compose(RxUtils.<ProjectListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ProjectListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ProjectListBean projectListBean) {
                        callBack.onSuccess(projectListBean);
                    }
                });
    }

}
