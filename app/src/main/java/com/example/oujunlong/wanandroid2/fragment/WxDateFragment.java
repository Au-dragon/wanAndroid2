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
import com.example.oujunlong.wanandroid2.activity.ShowActivity;
import com.example.oujunlong.wanandroid2.adapter.WxRvAdapter;
import com.example.oujunlong.wanandroid2.base.BaseFragment;
import com.example.oujunlong.wanandroid2.bean.WxDateBean;
import com.example.oujunlong.wanandroid2.presenter.WxDatePresenter;
import com.example.oujunlong.wanandroid2.view.WxDateView;
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
public class WxDateFragment extends BaseFragment<WxDateView, WxDatePresenter> implements WxDateView {

    @BindView(R.id.wxRecycler)
    RecyclerView wxRecycler;
    @BindView(R.id.mySmart)
    SmartRefreshLayout mySmart;
    @BindView(R.id.fab)
    FloatingActionButton fab;
private int i=1;
    private ArrayList<WxDateBean.DataBean.DatasBean> list = new ArrayList<>();
    private WxRvAdapter adapter;

    @Override
    protected WxDatePresenter initPresenter() {
        return new WxDatePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wxdate;
    }

    @Override
    protected void initView() {
        wxRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WxRvAdapter(list, getContext());
        wxRecycler.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
                adapter.notifyDataSetChanged();
                wxRecycler.smoothScrollToPosition(0);
            }
        });
        wxRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        adapter.setOnItemClickListener(new WxRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ShowActivity.class);
                intent.putExtra("Link",list.get(position).getLink());
                intent.putExtra("Author",list.get(position).getAuthor());
                intent.putExtra("superName",list.get(position).getSuperChapterName() + "/" + list.get(position).getChapterName());
                intent.putExtra("Title",list.get(position).getTitle());
                intent.putExtra("NiceDate",list.get(position).getNiceDate());
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

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        String id = (String) arguments.get("id");
        mPresenter.getData(id,i+"");

    }

    @Override
    public void onSuccess(WxDateBean bean) {
        list.addAll(bean.getData().getDatas());
        adapter.setList(list);
        mySmart.finishRefresh();
        mySmart.finishLoadmore();
    }

}
