package com.example.oujunlong.wanandroid2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.bean.RegisterBean;
import com.example.oujunlong.wanandroid2.http.ApiService;
import com.example.oujunlong.wanandroid2.http.BaseObserver;
import com.example.oujunlong.wanandroid2.http.HttpUtils;
import com.example.oujunlong.wanandroid2.http.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImg;
    /**
     * 注册
     */
    private TextView mT1;
    /**
     * 邮箱/手机号
     */
    private EditText mEd1;
    /**
     * 输入密码
     */
    private EditText mEd2;
    /**
     * 确认密码
     */
    private EditText mEd3;
    /**
     * 注册
     */
    private Button mB1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mImg = (ImageView) findViewById(R.id.img);
        mT1 = (TextView) findViewById(R.id.t1);
        mEd1 = (EditText) findViewById(R.id.ed1);
        mEd2 = (EditText) findViewById(R.id.ed2);
        mEd3 = (EditText) findViewById(R.id.ed3);
        mB1 = (Button) findViewById(R.id.b1);
        mB1.setOnClickListener(this);
        mImg.setOnClickListener(new View.OnClickListener() {
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
            case R.id.b1:
                initData();
                break;
        }
    }
    public void initData(){
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.logUrl, ApiService.class);
        String name= mEd1.getText().toString();
        String password= mEd2.getText().toString();
        String repassword= mEd3.getText().toString();

        Observable<RegisterBean> registerBean = apiserver.getRegisterBean(name, password, repassword);
        registerBean.compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if(registerBean.getErrorCode()==0){
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(RegisterActivity.this, registerBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
