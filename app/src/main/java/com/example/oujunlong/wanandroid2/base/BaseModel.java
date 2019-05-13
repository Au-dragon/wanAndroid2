package com.example.oujunlong.wanandroid2.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * creation time 2019/5/4
 * author oujunlong
 */
public class BaseModel {
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public void onDestory() {
        mCompositeDisposable.clear();
    }
}
