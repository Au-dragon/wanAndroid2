package com.example.oujunlong.wanandroid2.base;

import android.os.Environment;

import com.example.oujunlong.wanandroid2.app.MyApp;

import java.io.File;

/**
 * Created by asus on 2019/3/5.
 */

public interface Constants {
    boolean isDebug = true;


    String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + "codeest" + File.separator + "GeekNews";

    String FILE_PROVIDER_AUTHORITY="com.baidu.geek.fileprovider";

    //网络缓存的地址
    String PATH_DATA = MyApp.getMyApp().getCacheDir().getAbsolutePath() +
            File.separator + "data";

    String PATH_CACHE = PATH_DATA + "/NetCache";
    String DATA = "data";
    //夜间模式
    String MODE = "mode";
    String NIGHT_CURRENT_FRAG_POS = "fragment_pos";
}
