package com.example.oujunlong.wanandroid2.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.bean.CommonlyBean;
import com.example.oujunlong.wanandroid2.http.ApiService;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommonlyActivity extends AppCompatActivity {

    /**
     * 常用网站
     */
    private TextView mTv;
    private Toolbar mToolbar;
    private RecyclerView mMyRecycler;
    private ArrayList<CommonlyBean.DataBean> list = new ArrayList<>();
    private FlowLayout mMyflowlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commonly);
        initView();
    }

    private void initView() {

        mTv = (TextView) findViewById(R.id.tv);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mMyRecycler = (RecyclerView) findViewById(R.id.myRecycler);
        mMyflowlayout = (FlowLayout) findViewById(R.id.myflowlayout);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getData();
    }

    public void getData() {
        Retrofit build = new Retrofit.Builder().baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        Observable<CommonlyBean> commonlyBeanData = apiService.getCommonlyBeanData();
        commonlyBeanData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonlyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommonlyBean commonlyBean) {
                        Log.i("TAG","============"+commonlyBean.getData().size());
                        mMyflowlayout.removeAllViews();
                        List<CommonlyBean.DataBean> data = commonlyBean.getData();
                        for (int i = 0; i < data.size(); i++) {
                            int[] array = {R.color.Amber, R.color.arrow_color, R.color.colorPrimary
                                    , R.color.colorPrimaryDark, R.color.Blue, R.color.color_title_bg, R.color.Green
                                    , R.color.Deep_Orange, R.color.Lime, R.color.Teal, R.color.Indigo, R.color.Pink
                                    , R.color.Yellow, R.color.Amber, R.color.Purple, R.color.Light_Green
                                    , R.color.Light_Blue};

                            int random = (int) (Math.random() * (array.length - 1));
                            View inflate = LayoutInflater.from(CommonlyActivity.this).inflate(R.layout.item_commonly, null);
                            TextView tv = inflate.findViewById(R.id.t1);
                            tv.setText(data.get(i).getName());
                            tv.setBackgroundColor(getResources().getColor(array[random]));
                            mMyflowlayout.addView(inflate);
                        }
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
