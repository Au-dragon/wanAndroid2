package com.example.oujunlong.wanandroid2.http;





public interface ResultCallBack<T> {
    void onSuccess(T bean);
    void onFail(String msg);
}
