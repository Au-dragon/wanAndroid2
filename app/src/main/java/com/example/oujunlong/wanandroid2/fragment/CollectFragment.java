package com.example.oujunlong.wanandroid2.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.adapter.CollectAdapter;
import com.example.oujunlong.wanandroid2.bean.DaoBean;
import com.example.oujunlong.wanandroid2.utils.UtilsDao;

import java.util.ArrayList;
import java.util.List;

/**
 * creation time 2019/5/9
 * author oujunlong
 */
public class CollectFragment extends Fragment {
    private View view;
    private RecyclerView mMyRecycler;
    private ArrayList<DaoBean> list = new ArrayList<>();
    private CollectAdapter collectAdapter;
    private FloatingActionButton mFab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.fragment_collect, null);
        initView(inflate);
        initData();
        return inflate;
    }



    private void initView(View inflate) {
        mMyRecycler = (RecyclerView) inflate.findViewById(R.id.myRecycler);
        mFab = (FloatingActionButton) inflate.findViewById(R.id.fab);
        mMyRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        collectAdapter = new CollectAdapter(list, getContext());
        mMyRecycler.setAdapter(collectAdapter);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyRecycler.smoothScrollToPosition(0);
            }
        });
        mMyRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mFab.setVisibility(View.GONE);
            }
        });
    }

    public void initData() {
        list.clear();
        List<DaoBean> query = UtilsDao.getUtilsDao().query();
        list.addAll(query);
        //Log.i("TAG","============================================daoben"+list.size());
        if(collectAdapter!=null){
            collectAdapter.setList(list);
        }

    }

}
