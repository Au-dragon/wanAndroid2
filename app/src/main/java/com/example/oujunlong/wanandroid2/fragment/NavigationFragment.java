package com.example.oujunlong.wanandroid2.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.adapter.NaviagtionAdapter;
import com.example.oujunlong.wanandroid2.base.BaseFragment;
import com.example.oujunlong.wanandroid2.bean.NavigationBean;
import com.example.oujunlong.wanandroid2.presenter.NavigationPresenter;
import com.example.oujunlong.wanandroid2.view.NavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class NavigationFragment extends BaseFragment<NavigationView, NavigationPresenter> implements NavigationView {

    @BindView(R.id.myVeTabLayout)
    VerticalTabLayout myVeTabLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.myRecycler)
    RecyclerView myRecycler;
    private boolean isScroll;
private ArrayList<NavigationBean.DataBean> list=new ArrayList<>();
    private NaviagtionAdapter adapter;

    @Override
    protected NavigationPresenter initPresenter() {
        return new NavigationPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initData() {
mPresenter.getDatas();
    }


    @Override
    protected void initView() {
        myRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NaviagtionAdapter(list, getContext());
        myRecycler.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecycler.smoothScrollToPosition(0);
            }
        });
        myVeTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) myRecycler.getLayoutManager();
                layoutManager.scrollToPositionWithOffset(position, 0);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
        myRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //重写该方法主要是判断recyclerview是否在滑动
                //0停止 ，1,2都是滑动
                if (newState == 0) {
                    isScroll = false;
                } else {
                    isScroll = true;
                }
                LinearLayoutManager layoutManager = (LinearLayoutManager) myRecycler.getLayoutManager();
                //可见的第一条条目
                int top = layoutManager.findFirstVisibleItemPosition();
                myVeTabLayout.setTabSelected(top);
            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
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
                //这个主要是recyclerview滑动时让tab定位的方法

               /*recyclerView : 当前滚动的view
                dx : 水平滚动距离
                dy : 垂直滚动距离
                dx > 0 时为手指向左滚动,列表滚动显示右面的内容
                dx < 0 时为手指向右滚动,列表滚动显示左面的内容
                dy > 0 时为手指向上滚动,列表滚动显示下面的内容
                dy < 0 时为手指向下滚动,列表滚动显示上面的内容*/
                LinearLayoutManager layoutManager = (LinearLayoutManager) myRecycler.getLayoutManager();
                //可见的第一条条目
                int top = layoutManager.findFirstVisibleItemPosition();
                //可见的最后一条条目
                int bottom = layoutManager.findLastVisibleItemPosition();
                if (isScroll) {
                    if (dy > 0) {
                        myVeTabLayout.setTabSelected(top);
                    }
                }
            }
        });
    }

    @Override
    public void onSuccess(final NavigationBean bean) {
        ArrayList<NavigationBean.DataBean> data = (ArrayList<NavigationBean.DataBean>) bean.getData();
        adapter.setList(data);
        myVeTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return bean.getData().size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder()
                        .setContent(bean.getData().get(position).getName())
                        .build();
            }

            @Override

            public int getBackground(int position) {
                return 0;
            }
        });


    }
}
