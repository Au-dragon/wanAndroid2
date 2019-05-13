package com.example.oujunlong.wanandroid2.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.adapter.WxVpAdapter;
import com.example.oujunlong.wanandroid2.base.BaseFragment;
import com.example.oujunlong.wanandroid2.bean.WxBean;
import com.example.oujunlong.wanandroid2.presenter.WxPresenter;
import com.example.oujunlong.wanandroid2.view.WxView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class WxFragment extends BaseFragment<WxView, WxPresenter> implements WxView{
    @BindView(R.id.myTab)
    TabLayout myTab;
    @BindView(R.id.ed_text)
    EditText edText;
    @BindView(R.id.b1)
    Button b1;
    @BindView(R.id.myViewPager)
    ViewPager myViewPager;
  private ArrayList<WxBean.DataBean> list=new ArrayList<>();
  private ArrayList<Fragment> fragments=new ArrayList<>();
    private WxVpAdapter adapter;
    private FloatingActionButton viewById;

    @Override
    protected WxPresenter initPresenter() {
        return new WxPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wx;
    }

    @Override
    protected void initData() {
    mPresenter.getDatas();
    }

    @Override
    protected void initView() {
        //viewById = getActivity().findViewById(R.id.fab);
        adapter = new WxVpAdapter(getChildFragmentManager(), list, fragments);
        myViewPager.setAdapter(adapter);
        myTab.setupWithViewPager(myViewPager);
        myTab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                edText.setHint(list.get(position).getName()+"带你发现更多干货");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onSuccess(WxBean bean) {
        list.addAll(bean.getData());
        for (int i = 0; i < list.size(); i++) {
            WxDateFragment wxFragment = new WxDateFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id",list.get(i).getId()+"");
            wxFragment.setArguments(bundle);
            fragments.add(wxFragment);
        }
adapter.setList(list);
    }
}
