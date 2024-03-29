package com.example.oujunlong.wanandroid2.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * creation time 2019/4/29
 * author oujunlong
 */
public abstract class BaseFragment<V extends BaseView,P extends BasePresenter> extends Fragment implements BaseView{
    private Unbinder mUnbinder;
    public P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), null);
        mUnbinder = ButterKnife.bind(this, inflate);
        mPresenter = initPresenter();
        if (mPresenter != null){
            mPresenter.bind((V)this);
        }
        initView();
        initListener();
        initData();
        return inflate;
    }

    protected void initData() {

    }

    protected void initListener() {

    }

    protected void initView(){};

    protected abstract P initPresenter();

    protected abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        mPresenter.onDestory();
        mPresenter=null;
    }
}
