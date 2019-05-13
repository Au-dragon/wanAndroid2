package com.example.oujunlong.wanandroid2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.oujunlong.wanandroid2.activity.CommonlyActivity;
import com.example.oujunlong.wanandroid2.activity.LoginActivity;
import com.example.oujunlong.wanandroid2.activity.ScrollingActivity;
import com.example.oujunlong.wanandroid2.activity.SearchActivity;
import com.example.oujunlong.wanandroid2.base.BaseActivity;
import com.example.oujunlong.wanandroid2.bean.ListData;
import com.example.oujunlong.wanandroid2.fragment.CollectFragment;
import com.example.oujunlong.wanandroid2.fragment.HomeFragment;
import com.example.oujunlong.wanandroid2.fragment.NavigationFragment;
import com.example.oujunlong.wanandroid2.fragment.ProjectFragment;
import com.example.oujunlong.wanandroid2.fragment.SettingFragment;
import com.example.oujunlong.wanandroid2.fragment.WxFragment;
import com.example.oujunlong.wanandroid2.fragment.ZhishiFragment;
import com.example.oujunlong.wanandroid2.presenter.MainPresenter;
import com.example.oujunlong.wanandroid2.utils.CircularAnimUtil;
import com.example.oujunlong.wanandroid2.utils.Constants;
import com.example.oujunlong.wanandroid2.utils.SpUtil;
import com.example.oujunlong.wanandroid2.view.MainView;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

import butterknife.BindView;

//5cc798f13fc1951d96000396
public class MainActivity extends BaseActivity<MainView, MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, MainView {
    @BindView(R.id.myFragment)
    FrameLayout myFragment;
    private ArrayList<ListData.DatasBean> list = new ArrayList<>();
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.myTabLayout)
    TabLayout tabLayout;
    @BindView(R.id.toolbar_text)
    TextView toolbar_text;
    private HomeFragment homeFragment;
    private ZhishiFragment zhishiFragment;
    private WxFragment wxFragment;
    private NavigationFragment navigationFragment;
    private ProjectFragment projectFragment;
    private SettingFragment settingFragment;
    private final int HOMEFRAGMENT=0;
    private final int ZHISHIFRAGMENT=1;
    private final int WXFRAGMENT=2;
    private final int NAVIGATIONFRAGMENT=3;
    private final int PROJECTFRAGMENT=4;
    public static final int SETTINGFRAGMENT=5;
    public static final int COLLECTFRAGMENT=6;
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private int newsPost;
    private CollectFragment collectFragment;
    private TextView nav_text;


    @SuppressLint({"RestrictedApi", "ClickableViewAccessibility"})
    @Override
    public void initView() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.myTabLayout);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //fab = (FloatingActionButton) findViewById(R.id.fab);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

         navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        getTabLayout();
        setMyFragment();
        setNaviHeader();
        setWandroid();

    }




    private void setWandroid() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.myFragment,fragments.get(0));
        fragmentTransaction.commit();
    }

    private void setNaviHeader() {
        navView.getMenu().findItem(R.id.nav_tuichu).setVisible(true);
        View view = navView.inflateHeaderView(R.layout.nav_header_main);
        nav_text = view.findViewById(R.id.textView);
        if(nav_text.getText().equals("登录")){
            navView.getMenu().findItem(R.id.nav_tuichu).setVisible(false);
            nav_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivityForResult(intent,1);
                }
            });
        }else {
            navView.getMenu().findItem(R.id.nav_tuichu).setVisible(true);

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==2){
            String name = data.getStringExtra("name");
            nav_text.setText(name);
            navView.getMenu().findItem(R.id.nav_tuichu).setVisible(true);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void getTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setCustomView(setTabLayout(0)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(setTabLayout(1)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(setTabLayout(2)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(setTabLayout(3)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(setTabLayout(4)));
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        showFragment(HOMEFRAGMENT);
                        toolbar_text.setText(R.string.tab_shouye);
                        break;
                    case 1:
                        showFragment(ZHISHIFRAGMENT);
                        toolbar_text.setText(R.string.tab_zhishitixi);
                        break;
                    case 2:
                        showFragment(WXFRAGMENT);
                        toolbar_text.setText(R.string.tab_gongzhonghao);
                        break;
                    case 3:
                        showFragment(NAVIGATIONFRAGMENT);
                        toolbar_text.setText(R.string.tab_daohang);
                        break;
                    case 4:
                        showFragment(PROJECTFRAGMENT);
                        toolbar_text.setText(R.string.tab_xiangmu);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected MainPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }



    @Override
    public void onBackPressed() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            SpUtil.setParam(Constants.DAY_NIGHT_FRAGMENT_POS,HOMEFRAGMENT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, CommonlyActivity.class);
            intent.setClass(MainActivity.this, CommonlyActivity.class);
            CircularAnimUtil.startActivity(MainActivity.this, intent,toolbar, R.color.white);
            return true;
        }else if(id==R.id.action_sousuo){
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            intent.setClass(MainActivity.this, SearchActivity.class);
            CircularAnimUtil.startActivity(MainActivity.this, intent,toolbar, R.color.white);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("RestrictedApi")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        item.setChecked(true);
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            homeFragment.initData();
            showFragment(HOMEFRAGMENT);
            toolbar_text.setText(R.string.tab_shouye);
            tabLayout.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_gallery) {
            collectFragment.initData();
            showFragment(COLLECTFRAGMENT);
            tabLayout.setVisibility(View.GONE);
            toolbar_text.setText(R.string.shoucang);

        } else if (id == R.id.nav_slideshow) {
            showFragment(SETTINGFRAGMENT);
            tabLayout.setVisibility(View.GONE);

            toolbar_text.setText(R.string.shezhi);

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(this, ScrollingActivity.class);
            startActivity(intent);
        }else if(id==R.id.nav_tuichu){
            View view = LayoutInflater.from(this).inflate(R.layout.pop, null);
            Button b1 = view.findViewById(R.id.b1);
            Button b2 = view.findViewById(R.id.b2);
            final PopupWindow pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            pop.setFocusable(true);
            pop.setBackgroundDrawable(null);
            pop.setOutsideTouchable(true);
            pop.showAtLocation(drawerLayout, Gravity.CENTER, 0, 0);
            WindowManager.LayoutParams att = getWindow().getAttributes();
            att.alpha = 0.1f;
            getWindow().setAttributes(att);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nav_text.setText(R.string.denglu);
                    navView.getMenu().findItem(R.id.nav_tuichu).setVisible(false);
                    pop.dismiss();
                    WindowManager.LayoutParams att = getWindow().getAttributes();
                    att.alpha = 1f;
                    getWindow().setAttributes(att);
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.dismiss();
                    WindowManager.LayoutParams att = getWindow().getAttributes();
                    att.alpha = 1f;
                    getWindow().setAttributes(att);
                }
            });
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }



    @Override
    public void onSuccess(ListData listData) {
        list.addAll(listData.getDatas());
    }

public View setTabLayout(int postion){
    View inflate = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
    ImageView tab_img = inflate.findViewById(R.id.tab_img);
    TextView tabt_text= inflate.findViewById(R.id.tab_text);
    switch (postion) {
        case 0:
            tab_img.setImageResource(R.drawable.na_bottom_select);
            tabt_text.setText(R.string.tab_shouye);
            break;
        case 1:
            tab_img.setImageResource(R.drawable.na_bottom_select2);
            tabt_text.setText(R.string.tab_zhishitixi);
            break;
        case 2:
            tab_img.setImageResource(R.drawable.na_bottom_select3);
            tabt_text.setText(R.string.tab_gongzhonghao);
            break;
        case 3:
            tab_img.setImageResource(R.drawable.na_bottom_select4);
            tabt_text.setText(R.string.tab_daohang);
            break;
        case 4:
            tab_img.setImageResource(R.drawable.na_bottom_select5);
            tabt_text.setText(R.string.tab_xiangmu);
            break;
    }
    return inflate;
}
public void showFragment(int type){

    Fragment fragment = fragments.get(type);
    Fragment fragment1 = fragments.get(newsPost);
    FragmentManager supportFragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
    if(!fragment.isAdded()){
        fragmentTransaction.add(R.id.myFragment,fragment);
    }
    fragmentTransaction.show(fragment);
    fragmentTransaction.hide(fragment1);
    fragmentTransaction.commit();
    if(newsPost!=type){
        newsPost=type;
    }else {
        FragmentManager supportFragmentManager1 = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1 = supportFragmentManager1.beginTransaction();
        Fragment fragment2 = fragments.get(newsPost);
        if(!fragment2.isAdded()){
            fragmentTransaction1.add(R.id.myFragment,fragment2);
        }
        fragmentTransaction1.show(fragment2);
        fragmentTransaction1.commit();
    }

}
    public void setMyFragment(){
        homeFragment = new HomeFragment();
        zhishiFragment = new ZhishiFragment();
        wxFragment = new WxFragment();
        navigationFragment = new NavigationFragment();
        projectFragment = new ProjectFragment();
        settingFragment = new SettingFragment();
        collectFragment = new CollectFragment();
        fragments.add(homeFragment);
        fragments.add(zhishiFragment);
        fragments.add(wxFragment);
        fragments.add(navigationFragment);
        fragments.add(projectFragment);
        fragments.add(settingFragment);
        fragments.add(collectFragment);
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        homeFragment.initData();

    }
    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
