package com.example.oujunlong.wanandroid2.view;

import com.example.oujunlong.wanandroid2.base.BaseView;
import com.example.oujunlong.wanandroid2.bean.HomeBannerBean;
import com.example.oujunlong.wanandroid2.bean.HomeListBean;

import java.util.List;

/**
 * creation time 2019/5/3
 * author oujunlong
 */
public interface HomeView extends BaseView {
    void onHomeBannerSuccess(HomeBannerBean homeBannerBeans);
    void onHomeListSuccess(HomeListBean homeListBeans);
}
