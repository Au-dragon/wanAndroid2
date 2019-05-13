package com.example.oujunlong.wanandroid2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oujunlong.wanandroid2.R;

public class Show2Activity extends AppCompatActivity {

    private ImageView mImg;
    private Toolbar mMyToolBar;
    private WebView mMyWebView;
    private String url;
    private String title;
    /**
     * dasdasdasdasdasdasdasd
     */
    private TextView mT1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show2);
        initView();
    }

    private void initView() {
        mT1 = (TextView) findViewById(R.id.t1);
        mImg = (ImageView) findViewById(R.id.img);
        mMyToolBar = (Toolbar) findViewById(R.id.myToolBar);
        mMyWebView = (WebView) findViewById(R.id.myWebView);
        getData();
        mMyToolBar.setTitle("");
        setSupportActionBar(mMyToolBar);
        mT1.setText(title);
        mMyWebView.getSettings().setJavaScriptEnabled(true);
        mMyWebView.setWebViewClient(new WebViewClient());
        mMyWebView.loadUrl(url);
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_op_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
