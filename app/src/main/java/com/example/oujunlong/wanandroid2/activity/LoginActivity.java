package com.example.oujunlong.wanandroid2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.bean.LoginBean;
import com.example.oujunlong.wanandroid2.http.ApiService;
import com.example.oujunlong.wanandroid2.http.BaseObserver;
import com.example.oujunlong.wanandroid2.http.HttpUtils;
import com.example.oujunlong.wanandroid2.http.RxUtils;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImg1;

    /**
     * 请输入用户名
     */
    private EditText mEd1;
    /**
     * 请输入用户名
     */
    private EditText mEd2;
    /**
     * 登录
     */
    private Button mB1;
    /**
     * 注册
     */
    private Button mB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mImg1 = (ImageView) findViewById(R.id.img1);
        mEd1 = (EditText) findViewById(R.id.ed1);
        mEd2 = (EditText) findViewById(R.id.ed2);
        mB1 = (Button) findViewById(R.id.b1);
        mB2 = (Button) findViewById(R.id.b2);
        mB2.setOnClickListener(this);
        mB1.setOnClickListener(this);
        mImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.b2:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.b1:
getDatas();
                break;
        }
    }
    public void getDatas(){
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.logUrl, ApiService.class);
        String name = mEd1.getText().toString();
        String password = mEd2.getText().toString();

        Observable<LoginBean> loginBean = apiserver.getLoginBean(name, password);
        loginBean.compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        int errorCode = loginBean.getErrorCode();
                        if(errorCode==0){
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent = getIntent();
                            intent.putExtra("name",loginBean.getData().getUsername());
                            setResult(2,intent);
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this, loginBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
