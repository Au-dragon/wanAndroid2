package com.example.oujunlong.wanandroid2.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.activity.ZhiShiActivity;
import com.example.oujunlong.wanandroid2.adapter.ZhiShiAdapter;
import com.example.oujunlong.wanandroid2.base.BaseFragment;
import com.example.oujunlong.wanandroid2.bean.ZhishiBean;
import com.example.oujunlong.wanandroid2.presenter.ZhishiPresenter;
import com.example.oujunlong.wanandroid2.view.ZhishiView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class ZhishiFragment extends BaseFragment<ZhishiView, ZhishiPresenter> implements ZhishiView {
    @BindView(R.id.myRecycler)
    RecyclerView myRecycler;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private ArrayList<ZhishiBean.DataBean> list = new ArrayList<>();
    private ZhiShiAdapter adapter;

    @Override
    protected ZhishiPresenter initPresenter() {
        return new ZhishiPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        myRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ZhiShiAdapter(list, getContext());
        myRecycler.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecycler.smoothScrollToPosition(0);
            }
        });
        myRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0) {
                    //上滑监听
                    getActivity().findViewById(R.id.myTabLayout).setVisibility(View.VISIBLE);

                    fab.setVisibility(View.VISIBLE);
                } else if (dy > 0) {
                    //下滑监听
                    getActivity().findViewById(R.id.myTabLayout).setVisibility(View.GONE);
                    fab.setVisibility(View.GONE);
                }
            }
        });
        adapter.setOnItemClickListener(new ZhiShiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ZhiShiActivity.class);
                ArrayList<ZhishiBean.DataBean.ChildrenBean> children = (ArrayList<ZhishiBean.DataBean.ChildrenBean>) list.get(position).getChildren();
                intent.putExtra("list", children);
                intent.putExtra("title", list.get(position).getName());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getDatas();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhishi;

    }


    @Override
    public void onSuccess(ZhishiBean zhiShiBean) {
        list.addAll(zhiShiBean.getData());
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }


}
