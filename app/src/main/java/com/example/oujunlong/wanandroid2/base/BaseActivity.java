package com.example.oujunlong.wanandroid2.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * creation time 2019/4/29
 * author oujunlong
 */
public abstract class BaseActivity<V extends BaseView,P extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter = initPresenter();
        if (mPresenter != null){
            //直接强转不对,因为BaseActivity不作为页面展示,展示的都是他的子类,
            // 而子类必须实现BaseMvpView
            mPresenter.bind((V) this);
        }
        initData();
        initView();
        initListener();

    }

    protected abstract P initPresenter();

    protected void initData() {

    }

    protected void initListener() {

    }

    protected void initView(){
    };

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //切断V层和P层的联系
if(mPresenter!=null){
    mPresenter.onDestory();
    mPresenter = null;
}

    }
}
