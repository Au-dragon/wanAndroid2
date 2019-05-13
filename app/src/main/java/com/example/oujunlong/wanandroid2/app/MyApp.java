package com.example.oujunlong.wanandroid2.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.example.oujunlong.wanandroid2.base.Constants;
import com.example.oujunlong.wanandroid2.utils.SpUtil;
import com.example.oujunlong.wanandroid2.utils.UIModeUtil;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;

/**
 * creation time 2019/4/29
 * author oujunlong
 */
public class MyApp extends Application {
    private static MyApp myApp;
    //默认不是夜间模式
    public static int mMode = AppCompatDelegate.MODE_NIGHT_NO;
    public static int mWidthPixels;
    public static int mHeightPixels;
    @Override
    public void onCreate() {
        UMConfigure.init(this,"5cc7aaa10cafb249400008a0", "", UMConfigure.DEVICE_TYPE_PHONE, null);
        CrashReport.initCrashReport(this, "4feb712f3b", true);
        super.onCreate();
        myApp=this;
        getScreenWH();
        setDayNightMode();

    }
    private void setDayNightMode() {
        //根据sp里面的模式设置对应的日夜间模式
        mMode = (int) SpUtil.getParam(Constants.MODE, AppCompatDelegate.MODE_NIGHT_NO);
        UIModeUtil.setAppMode(mMode);
    }

    //屏幕宽高
    private void getScreenWH() {
        WindowManager manger = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display defaultDisplay = manger.getDefaultDisplay();
        DisplayMetrics metics = new DisplayMetrics();
        defaultDisplay.getMetrics(metics);
        mWidthPixels = metics.widthPixels;
        mHeightPixels = metics.heightPixels;
    }
    public static MyApp getMyApp(){
        return myApp;
    }
}
