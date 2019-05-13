package com.example.oujunlong.wanandroid2.presenter;

import com.example.oujunlong.wanandroid2.base.BasePresenter;
import com.example.oujunlong.wanandroid2.bean.HomeBannerBean;
import com.example.oujunlong.wanandroid2.bean.HomeListBean;
import com.example.oujunlong.wanandroid2.model.HomeModel;
import com.example.oujunlong.wanandroid2.view.HomeView;

import java.util.List;

/**
 * creation time 2019/5/3
 * author oujunlong
 */
public class HomePresenter extends BasePresenter<HomeView> implements HomeModel.CallBack {
private HomeModel homeModel;
private HomeView homeView;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
    }

    public void getDatas(int i){
    homeModel.getHomeBannerDatas(this);
    homeModel.getHomeListData(this,i);
}

    @Override
    public void getHomeBannerData(HomeBannerBean homeBannerBeans) {
        if(homeView!=null){
            homeView.onHomeBannerSuccess(homeBannerBeans);
        }

    }

    @Override
    public void getHomeListData(HomeListBean homeListBeans) {
        if(homeView!=null){
            homeView.onHomeListSuccess(homeListBeans);
        }

    }

    @Override
    protected void initModel() {
        homeModel=new HomeModel();
    }
}
