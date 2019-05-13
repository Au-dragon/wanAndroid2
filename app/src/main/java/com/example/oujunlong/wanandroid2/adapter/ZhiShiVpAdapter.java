package com.example.oujunlong.wanandroid2.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * creation time 2019/5/6
 * author oujunlong
 */
public class ZhiShiVpAdapter extends FragmentPagerAdapter {
    private ArrayList<String>tab;
    private ArrayList<Fragment> fragments;

    public ZhiShiVpAdapter(FragmentManager fm, ArrayList<String> tab, ArrayList<Fragment> fragments) {
        super(fm);
        this.tab = tab;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return tab.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tab.get(position);
    }
}
