package com.example.oujunlong.wanandroid2.base;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * creation time 2019/4/29
 * author oujunlong
 */
public abstract class BasePresenter<V extends BaseView> {
    protected V mView;
    protected ArrayList<BaseModel> mModels = new ArrayList<>();

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void bind(V view) {
        this.mView = view;
    }

    public void onDestory() {
        if(mView!=null){
            mView = null;
            if (mModels.size()>0){
                for (BaseModel model : mModels) {
                    model.onDestory();
                }
            }
        }

    }
}
