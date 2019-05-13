package com.example.oujunlong.wanandroid2.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.activity.ShowActivity;
import com.example.oujunlong.wanandroid2.adapter.HomeAdapter;
import com.example.oujunlong.wanandroid2.bean.HomeBannerBean;
import com.example.oujunlong.wanandroid2.bean.HomeListBean;
import com.example.oujunlong.wanandroid2.presenter.HomePresenter;
import com.example.oujunlong.wanandroid2.view.HomeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * creation time 2019/5/3
 * author oujunlong
 */
public class HomeFragment extends Fragment implements HomeView {
    private int i;
    private ArrayList<HomeBannerBean.DataBean> banners = new ArrayList<>();
    private ArrayList<HomeListBean.DataBean.DatasBean> homeListBean = new ArrayList<>();
    private HomeAdapter homeAdapter;
    private View view;
    private RecyclerView mEcyclerView;
    private SmartRefreshLayout mSmart;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, null);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onHomeBannerSuccess(HomeBannerBean homeBannerBeans) {
        if(homeBannerBeans!=null&&homeBannerBeans.getData().size()>0&&homeBannerBeans.getData()!=null){
            banners.addAll(homeBannerBeans.getData());
            homeAdapter.setBanners(banners);
        }

    }

    @Override
    public void onHomeListSuccess(HomeListBean homeListBeans) {
if(homeListBeans!=null){
    homeListBean.addAll(homeListBeans.getData().getDatas());
    homeAdapter.setHomeListBeans(homeListBean);
}


        mSmart.finishRefresh();
        mSmart.finishLoadmore();

    }

    private void initView(View view) {

        mEcyclerView = (RecyclerView) view.findViewById(R.id.ecyclerView);
        mSmart = (SmartRefreshLayout) view.findViewById(R.id.Smart);
        fab = view.findViewById(R.id.fab);
        mEcyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(banners, homeListBean, getContext());
        mEcyclerView.setAdapter(homeAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEcyclerView.smoothScrollToPosition(0);
            }
        });
        mSmart.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                i++;
                initData();
                homeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                i=0;
                homeListBean.clear();
                initData();
                homeAdapter.notifyDataSetChanged();

            }
        });

mEcyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {




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
        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(),ShowActivity.class);
                if(position!=0&&homeAdapter.banners.size()!=0){
                    intent.putExtra("Link",homeListBean.get(position-1).getLink());
                    intent.putExtra("Author",homeListBean.get(position-1).getAuthor());
                    intent.putExtra("superName",homeListBean.get(position-1).getSuperChapterName() + "/" + homeListBean.get(position-1).getChapterName());
                    intent.putExtra("Title",homeListBean.get(position-1).getTitle());
                    intent.putExtra("NiceDate",homeListBean.get(position-1).getNiceDate());
                    startActivity(intent);
                }

            }
        });
    }
    public void initData(){
        HomePresenter homePresenter=new HomePresenter(this);
        homePresenter.getDatas(i);
    }
}
