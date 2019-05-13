package com.example.oujunlong.wanandroid2.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oujunlong.wanandroid2.R;
import com.zhy.view.flowlayout.FlowLayout;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private ImageView mBack;
    private FlowLayout mFlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mFlay = (FlowLayout) findViewById(R.id.flay);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        ArrayList<String> list_search=new ArrayList<>();
        list_search.add("面试");
        list_search.add("Studio3");
        list_search.add("动画");
        list_search.add("自定义View");
        list_search.add("性能优化 速度");
        list_search.add("gradle");
        list_search.add("Camera 相机");
        list_search.add("代码混淆 安全");
        list_search.add("逆向 加固");
        for (int i = 0; i < list_search.size(); i++) {
            int[] array = {R.color.Amber, R.color.arrow_color, R.color.colorPrimary
                    , R.color.colorPrimaryDark, R.color.Blue, R.color.color_title_bg, R.color.Green
                    , R.color.Deep_Orange, R.color.Lime, R.color.Teal, R.color.Indigo, R.color.Pink
                    , R.color.Yellow, R.color.Amber, R.color.Purple, R.color.Light_Green
                    , R.color.Light_Blue};

            int random = (int) (Math.random() * (array.length - 1));
            View inflate1 = LayoutInflater.from(this).inflate(R.layout.tag_textview, null);
            TextView tv = inflate1.findViewById(R.id.tag_textview);
            tv.setText(list_search.get(i));
            tv.setTextColor(this.getResources().getColor(array[random]));
            mFlay.addView(tv);
        }
    }
}
