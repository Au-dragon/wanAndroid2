package com.example.oujunlong.wanandroid2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.adapter.ZhiShiVpAdapter;
import com.example.oujunlong.wanandroid2.base.BaseActivity;
import com.example.oujunlong.wanandroid2.base.BasePresenter;
import com.example.oujunlong.wanandroid2.bean.ZhishiBean;
import com.example.oujunlong.wanandroid2.fragment.ZhiShiDateFragment;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhiShiActivity extends BaseActivity {


    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.t1)
    TextView t1;
    @BindView(R.id.myToolBar)
    Toolbar myToolBar;
    @BindView(R.id.myTab)
    TabLayout myTab;
    @BindView(R.id.myVp)
    ViewPager myVp;
    private ArrayList<String> tab=new ArrayList<>();
    private ArrayList<ZhishiBean.DataBean.ChildrenBean> list;
    private ZhiShiDateFragment zhiShiDateFragment;
    private ArrayList<Fragment> fragments=new ArrayList<>();

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhi_shi;
    }

    @Override
    protected void initView() {

        ZhiShiVpAdapter zhiShiVpAdapter = new ZhiShiVpAdapter(getSupportFragmentManager(), tab, fragments);
        myVp.setAdapter(zhiShiVpAdapter);
        myTab.setupWithViewPager(myVp);
img.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        list = (ArrayList<ZhishiBean.DataBean.ChildrenBean>) intent.getSerializableExtra("list");
        String title = intent.getStringExtra("title");
        myToolBar.setTitle("");
        setSupportActionBar(myToolBar);
        t1.setText(title);
        if(tab.size()==0){
            for (int i = 0; i < list.size(); i++) {
                tab.add(list.get(i).getName());
                zhiShiDateFragment = new ZhiShiDateFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id",list.get(i).getId()+"");
                zhiShiDateFragment.setArguments(bundle);
                fragments.add(zhiShiDateFragment);
            }
        }


    }
}
