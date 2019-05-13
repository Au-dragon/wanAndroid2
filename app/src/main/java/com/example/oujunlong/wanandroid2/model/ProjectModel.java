package com.example.oujunlong.wanandroid2.model;

import com.example.oujunlong.wanandroid2.base.BaseModel;
import com.example.oujunlong.wanandroid2.bean.ProjectTabBean;
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
public class ProjectModel extends BaseModel {
public void getTabData(final ResultCallBack<ProjectTabBean> callBack){
    ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.URL, ApiService.class);
    Observable<ProjectTabBean> projectTabData = apiserver.getProjectTabData();
    projectTabData.compose(RxUtils.<ProjectTabBean>rxObserableSchedulerHelper())
            .subscribe(new BaseObserver<ProjectTabBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    mCompositeDisposable.add(d);
                }

                @Override
                public void onNext(ProjectTabBean projectTabBean) {
                    callBack.onSuccess(projectTabBean);
                }
            });

}

}
