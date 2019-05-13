package com.example.oujunlong.wanandroid2.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oujunlong.wanandroid2.MainActivity;
import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.utils.Constants;
import com.example.oujunlong.wanandroid2.utils.SpUtil;
import com.example.oujunlong.wanandroid2.utils.UIModeUtil;

import io.reactivex.disposables.CompositeDisposable;

/**
 * creation time 2019/5/7
 * author oujunlong
 */
public class SettingFragment extends Fragment  {
    private View view;
    /**
     * 通用设置
     */
    private TextView mTextView2;
    private ImageView mImageView;
    /**
     * 自动缓存
     */
    private TextView mT1;
    private CheckBox mC1;
    private ImageView mImageView2;
    /**
     * 无图模式
     */
    private TextView mT2;
    private CheckBox mC2;
    private ImageView mImageView3;
    /**
     * 夜间模式
     */
    private TextView mT3;
    private CheckBox mC3;
    private ImageView mImageView4;
    /**
     * 意见反馈
     */
    private TextView mT4;
    private CardView mCard;
    private ImageView mImageView5;
    /**
     * 清除缓存
     */
    private TextView mT5;
    /**
     * 0KB
     */
    private TextView mC5;
    private CardView mCard2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_settings, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

        mTextView2 = (TextView) view.findViewById(R.id.textView2);
        mImageView = (ImageView) view.findViewById(R.id.imageView);
        mT1 = (TextView) view.findViewById(R.id.t1);
        mC1 = (CheckBox) view.findViewById(R.id.c1);
        mImageView2 = (ImageView) view.findViewById(R.id.imageView2);
        mT2 = (TextView) view.findViewById(R.id.t2);
        mC2 = (CheckBox) view.findViewById(R.id.c2);
        mImageView3 = (ImageView) view.findViewById(R.id.imageView3);
        mT3 = (TextView) view.findViewById(R.id.t3);
        mC3 = (CheckBox) view.findViewById(R.id.c3);
        mImageView4 = (ImageView) view.findViewById(R.id.imageView4);
        mT4 = (TextView) view.findViewById(R.id.t4);
        mCard = (CardView) view.findViewById(R.id.card);
        mImageView5 = (ImageView) view.findViewById(R.id.imageView5);
        mT5 = (TextView) view.findViewById(R.id.t5);
        mC5 = (TextView) view.findViewById(R.id.c5);
        mCard2 = (CardView) view.findViewById(R.id.card2);
        Boolean huancun = (Boolean) SpUtil.getParam("huancun", false);
        if(huancun){
            mC1.setChecked(true);
        }else {
            mC1.setChecked(false);
        }
        mC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //缓存
                if(mC1.isChecked()){
                    SpUtil.setParam("huancun",true);
                    Toast.makeText(getContext(), "开启缓存", Toast.LENGTH_SHORT).show();
                }else {
                    SpUtil.setParam("huancun",false);
                    Toast.makeText(getContext(), "关闭缓存", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Boolean wutu = (Boolean) SpUtil.getParam("wutu", false);
        if(wutu){
            mC2.setChecked(true);
        }else {
            mC2.setChecked(false);
        }
        mC2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(mC2.isChecked()){
                   SpUtil.setParam("wutu",true);
                   Toast.makeText(getContext(), "开启无图模式", Toast.LENGTH_SHORT).show();
               }else {
                   SpUtil.setParam("wutu",false);
                   Toast.makeText(getContext(), "关闭无图模式", Toast.LENGTH_SHORT).show();
               }
           }
       });



        mC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切换模式
                //切换日夜间模式的时候Activity会重新创建
                //对应的这个碎片也会重建,重建的时候SwitchCompat会设置默认值
                //设置默认值的时候这个回调会被调用
                //if (用户点击的情况下){
                if (mC3.isPressed()){
                    //切换并保存模式
                    UIModeUtil.changeModeUI((MainActivity) getActivity());
                    //保存当前碎片的type
                    SpUtil.setParam(Constants.DAY_NIGHT_FRAGMENT_POS,MainActivity.SETTINGFRAGMENT);
                }
            }
        });
        int currentNightMode = getActivity().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
            //判断当前是日间模式
            mC3.setChecked(false);
        }else {
            mC3.setChecked(true);
        }

    }





}
