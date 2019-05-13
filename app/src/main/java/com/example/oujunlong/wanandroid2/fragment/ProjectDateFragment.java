package com.example.oujunlong.wanandroid2.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.activity.Show2Activity;
import com.example.oujunlong.wanandroid2.adapter.ProjectDateAdapter;
import com.example.oujunlong.wanandroid2.base.BaseFragment;
import com.example.oujunlong.wanandroid2.bean.ProjectListBean;
import com.example.oujunlong.wanandroid2.presenter.ProjectDatePresenter;
import com.example.oujunlong.wanandroid2.view.ProjectDateView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class ProjectDateFragment extends BaseFragment<ProjectDateView, ProjectDatePresenter> implements ProjectDateView {
    @BindView(R.id.myRecycler)
    RecyclerView myRecycler;
    @BindView(R.id.mySmart)
    SmartRefreshLayout mySmart;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private int i = 1;
    private ArrayList<ProjectListBean.DataBean.DatasBean> list = new ArrayList<>();
    private ProjectDateAdapter adapter;


    @Override
    protected ProjectDatePresenter initPresenter() {
        return new ProjectDatePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_projectdate;
    }

    @Override
    public void onSuccess(ProjectListBean bean) {
        list.addAll(bean.getData().getDatas());
        adapter.setList(list);
        mySmart.finishLoadmore();
        mySmart.finishRefresh();
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        String id = (String) arguments.get("id");
        mPresenter.getData(id, i + "");
    }

    @Override
    protected void initView() {
        myRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProjectDateAdapter(list, getContext());
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
                if (dy<0){
                    //上滑监听
                    getActivity().findViewById(R.id.myTabLayout).setVisibility(View.VISIBLE);

                    fab.setVisibility(View.VISIBLE);
                }else if(dy>0){
                    //下滑监听
                    getActivity().findViewById(R.id.myTabLayout).setVisibility(View.GONE);
                    fab.setVisibility(View.GONE);
                }
            }
        });
        adapter.setOnItemClickListener(new ProjectDateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), Show2Activity.class);
                intent.putExtra("url",list.get(position).getLink());
                intent.putExtra("title",list.get(position).getTitle());
                startActivity(intent);
            }
        });
        mySmart.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                i++;
                initData();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                i=0;
                list.clear();
                initData();
                adapter.notifyDataSetChanged();
            }
        });
    }


}
