package com.example.oujunlong.wanandroid2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.bean.DaoBean;
import com.example.oujunlong.wanandroid2.utils.UtilsDao;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    private ImageView mImg;
    private CheckBox mImg2;
    private Toolbar mMyToolBar;
    private WebView mMyWebView;
    private String link;
    private String author;
    private String superName;
    private String title;
    private String niceDate;
    private ArrayList<DaoBean> list=new ArrayList<>();
    /**
     * dasdasdasdasdasdasdasd
     */
    private TextView mT1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
    }

    private void initView() {
        mImg = (ImageView) findViewById(R.id.img);
        mImg2 = (CheckBox) findViewById(R.id.img2);
        mMyToolBar = (Toolbar) findViewById(R.id.myToolBar);
        mMyWebView = (WebView) findViewById(R.id.myWebView);
        mT1 = (TextView) findViewById(R.id.t1);
        getData();
        mMyToolBar.setTitle("");
        setSupportActionBar(mMyToolBar);
        mT1.setText(title);
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        boolean has = UtilsDao.getUtilsDao().has(list.get(0));
        if(has){
            mImg2.setChecked(true);
        }else {
            mImg2.setChecked(false);
        }
        mImg2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if(mImg2.isChecked()){
            UtilsDao.getUtilsDao().insert(list.get(0));
        }else {

            List<DaoBean> querywhere = UtilsDao.getUtilsDao().querywhere(list.get(0));
            UtilsDao.getUtilsDao().delteone(querywhere);
        }

    }
});
    }

    public void getData() {
        Intent intent = getIntent();
        link = intent.getStringExtra("Link");
        Log.i("TAG","============"+link);
        author = intent.getStringExtra("Author");
        superName = intent.getStringExtra("superName");
        title = intent.getStringExtra("Title");
        niceDate = intent.getStringExtra("NiceDate");
        mMyWebView.getSettings().setJavaScriptEnabled(true);
        mMyWebView.setWebViewClient(new WebViewClient());
        mMyWebView.loadUrl(link);
        list.add(new DaoBean(null,author,superName,title,niceDate));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
getMenuInflater().inflate(R.menu.show_op_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
