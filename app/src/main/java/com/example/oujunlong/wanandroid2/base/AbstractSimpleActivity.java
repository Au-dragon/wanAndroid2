package com.example.oujunlong.wanandroid2.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.SupportActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;



/**
 * Common simple Activity
 *
 * @author quchao
 * @date 2017/11/28
 */

public abstract class AbstractSimpleActivity extends SupportActivity {

    /*private Unbinder unBinder;
    protected AbstractSimpleActivity mActivity;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);
        mActivity = this;
        ActivityCollector.getInstance().addActivity(this);
        onViewCreated();
        initToolbar();
        initEventAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
        if (unBinder != null && unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
            unBinder = null;
        }
    }



    *//**
     * 在initEventAndData()之前执行
     *//*
    protected abstract void onViewCreated();

    *//**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     *//*
    protected abstract int getLayoutId();

    *//**
     * 初始化ToolBar
     *//*
    protected abstract void initToolbar();

    *//**
     * 初始化数据
     *//*
    protected abstract void initEventAndData();
*/
}
