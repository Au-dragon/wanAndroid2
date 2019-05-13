package com.example.oujunlong.wanandroid2.model;


import com.example.oujunlong.wanandroid2.base.BaseModel;
import com.example.oujunlong.wanandroid2.bean.HomeBannerBean;
import com.example.oujunlong.wanandroid2.bean.HomeListBean;
import com.example.oujunlong.wanandroid2.http.ApiService;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * creation time 2019/5/3
 * author oujunlong
 */
public class HomeModel extends BaseModel {
    public interface CallBack{
        void getHomeBannerData(HomeBannerBean homeBannerBeans);
        void getHomeListData(HomeListBean homeListBeans);
    }
    public void getHomeBannerDatas(final CallBack callBack){
        Retrofit build = new Retrofit.Builder().baseUrl(ApiService.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        Observable<HomeBannerBean> homeBannerDatas = apiService.getHomeBannerDatas();
        homeBannerDatas.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HomeBannerBean homeBannerBean) {
                            callBack.getHomeBannerData(homeBannerBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public void getHomeListData(final CallBack callBack, int post){
        Retrofit build = new Retrofit.Builder().baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        Observable<HomeListBean> homeListDatas = apiService.getHomeListDatas("article/list/" + post + "/json");
        homeListDatas.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HomeListBean homeListBean) {
                            callBack.getHomeListData(homeListBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
