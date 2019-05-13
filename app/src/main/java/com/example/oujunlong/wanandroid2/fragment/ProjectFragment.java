package com.example.oujunlong.wanandroid2.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.adapter.ProjectVpAdapter;
import com.example.oujunlong.wanandroid2.base.BaseFragment;
import com.example.oujunlong.wanandroid2.bean.ProjectTabBean;
import com.example.oujunlong.wanandroid2.presenter.ProjectPresenter;
import com.example.oujunlong.wanandroid2.view.ProjectView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class ProjectFragment extends BaseFragment<ProjectView, ProjectPresenter> implements ProjectView {
    @BindView(R.id.myTab)
    TabLayout myTab;
    @BindView(R.id.myVp)
    ViewPager myVp;
    private ArrayList<ProjectTabBean.DataBean> list=new ArrayList<>();
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private ProjectDateFragment projectDateFragment;
    private ProjectVpAdapter adapter;

    @Override
    protected ProjectPresenter initPresenter() {
        return new ProjectPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    public void onSuccess(ProjectTabBean bean) {
        list.addAll(bean.getData());
        setFragments();
    }

    @Override
    protected void initView() {
        adapter = new ProjectVpAdapter(getChildFragmentManager(), list, fragments);
        myVp.setAdapter(adapter);
        myTab.setupWithViewPager(myVp);
        //设置tab栏的样式
            LinearLayout childAt = (LinearLayout) myTab.getChildAt(0);
            childAt.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            childAt.setDividerPadding(20);
            childAt.setDividerDrawable(ContextCompat.getDrawable(getContext(), R.drawable.tab_shu));


    }

    @Override
    protected void initData() {
        mPresenter.getData();

    }
    public void setFragments(){
        for (int i = 0; i < list.size(); i++) {
            projectDateFragment = new ProjectDateFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id",list.get(i).getId()+"");
            projectDateFragment.setArguments(bundle);
            fragments.add(projectDateFragment);
        }
        adapter.setList(list);
        adapter.setFragments(fragments);
        adapter.notifyDataSetChanged();
    }
}
