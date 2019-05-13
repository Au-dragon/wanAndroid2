package com.example.oujunlong.wanandroid2.http;

import com.example.oujunlong.wanandroid2.bean.CommonlyBean;
import com.example.oujunlong.wanandroid2.bean.HomeBannerBean;
import com.example.oujunlong.wanandroid2.bean.HomeListBean;
import com.example.oujunlong.wanandroid2.bean.ListData;
import com.example.oujunlong.wanandroid2.bean.LoginBean;
import com.example.oujunlong.wanandroid2.bean.NavigationBean;
import com.example.oujunlong.wanandroid2.bean.ProjectListBean;
import com.example.oujunlong.wanandroid2.bean.ProjectTabBean;
import com.example.oujunlong.wanandroid2.bean.RegisterBean;
import com.example.oujunlong.wanandroid2.bean.WxBean;
import com.example.oujunlong.wanandroid2.bean.WxDateBean;
import com.example.oujunlong.wanandroid2.bean.ZhiShiDateBean;
import com.example.oujunlong.wanandroid2.bean.ZhishiBean;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * creation time 2019/4/29
 * author oujunlong
 */
public interface ApiService {
    String URL = "http://www.wanandroid.com/";
    String logUrl = "https://www.wanandroid.com/";

    @POST("user/login")
    Observable<LoginBean> getLoginBean(@Query("username") String name, @Query("password") String password);

    @POST("user/register")
    Observable<RegisterBean> getRegisterBean(@Query("username") String name
            , @Query("password") String password
            , @Query("repassword") String repassword);

    @GET("banner/json")
    Observable<HomeBannerBean> getHomeBannerDatas();


    @GET()
    Observable<HomeListBean> getHomeListDatas(@Url String url);

    @GET("tree/json")
    Observable<ZhishiBean> getZhiShiBean();

    @GET("wxarticle/chapters/json ")
    Observable<WxBean> getWxBeanData();

    @GET()
    Observable<WxDateBean> getWxDateBean(@Url String url);

    @GET("navi/json")
    Observable<NavigationBean> getNaviagtionData();

    @GET("project/tree/json")
    Observable<ProjectTabBean> getProjectTabData();

    @GET()
    Observable<ProjectListBean> getProjectListBean(@Url String url);

    @GET()
    Observable<ZhiShiDateBean> getZhiShiDateDate(@Url String url);

    @GET("friend/json")
    Observable<CommonlyBean> getCommonlyBeanData();
}
