package com.example.oujunlong.wanandroid2.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.oujunlong.wanandroid2.bean.ProjectTabBean;

import java.util.ArrayList;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class ProjectVpAdapter extends FragmentPagerAdapter  {
    private ArrayList<ProjectTabBean.DataBean> list;
    private ArrayList<Fragment> fragments;

    public ProjectVpAdapter(FragmentManager fm, ArrayList<ProjectTabBean.DataBean> list, ArrayList<Fragment> fragments) {
        super(fm);
        this.list = list;
        this.fragments = fragments;
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public void setList(ArrayList<ProjectTabBean.DataBean> list) {
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName();
    }
}
