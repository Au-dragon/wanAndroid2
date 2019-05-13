package com.example.oujunlong.wanandroid2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.activity.Show2Activity;
import com.example.oujunlong.wanandroid2.activity.ShowActivity;
import com.example.oujunlong.wanandroid2.bean.DaoBean;
import com.example.oujunlong.wanandroid2.bean.HomeBannerBean;
import com.example.oujunlong.wanandroid2.bean.HomeListBean;
import com.example.oujunlong.wanandroid2.utils.SpUtil;
import com.example.oujunlong.wanandroid2.utils.UtilsDao;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;

/**
 * creation time 2019/5/4
 * author oujunlong
 */
public class HomeAdapter extends RecyclerView.Adapter {
    public ArrayList<HomeBannerBean.DataBean> banners;
    private ArrayList<HomeListBean.DataBean.DatasBean> homeListBeans;
    private ArrayList<HomeListBean.DataBean.DatasBean> tags;
    private Context context;
    private OnItemClickListener mOnItemClickListener;



    public HomeAdapter(ArrayList<HomeBannerBean.DataBean> banners, ArrayList<HomeListBean.DataBean.DatasBean> homeListBeans, Context context) {
        this.banners = banners;
        this.homeListBeans = homeListBeans;
        this.context = context;
    }

    public void setBanners(ArrayList<HomeBannerBean.DataBean> banners) {

        this.banners = banners;
        notifyDataSetChanged();
    }

    public void setHomeListBeans(ArrayList<HomeListBean.DataBean.DatasBean> homeListBeans) {
        this.homeListBeans = homeListBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_homebanner, null);
            return new MyViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_homelist, null);
            return new MyViewHolder2(view);
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final UtilsDao utilsDao = UtilsDao.getUtilsDao();
        int itemViewType = getItemViewType(i);
        if (itemViewType == 1) {
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            final ArrayList<String> list = new ArrayList<>();
            final ArrayList<String> title = new ArrayList<>();
            list.clear();
            for (int j = 0; j < banners.size(); j++) {
                list.add(banners.get(j).getImagePath());
                title.add(banners.get(j).getTitle());
            }
            myViewHolder.myBanner.setImages(list)
                    .setBannerTitles(title).setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Boolean wutu = (Boolean) SpUtil.getParam("wutu", false);
                    if(wutu){
                        return;
                    }
                    Glide.with(context).load(path).into(imageView);
                }
            }).start();
            myViewHolder.myBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    if (banners.size() != 0) {
                        Intent intent = new Intent(context, Show2Activity.class);
                        intent.putExtra("url", banners.get(position).getUrl());
                        intent.putExtra("title", banners.get(position).getTitle());
                        context.startActivity(intent);
                    }

                }
            });
        } else {
            MyViewHolder2 myViewHolder2 = (MyViewHolder2) viewHolder;
            int post = i-1;
            boolean has=false;
             final DaoBean   daoBean = new DaoBean(null, homeListBeans.get(post).getAuthor()
                        , homeListBeans.get(post).getSuperChapterName() + "/" + homeListBeans.get(post).getChapterName()
                        , homeListBeans.get(post).getTitle()
                        , homeListBeans.get(post).getNiceDate());
                has = utilsDao.has(daoBean);


            myViewHolder2.t1.setText(homeListBeans.get(post).getAuthor());
            myViewHolder2.t2.setText(homeListBeans.get(post).getSuperChapterName() + "/" + homeListBeans.get(post).getChapterName());
            myViewHolder2.t3.setText(homeListBeans.get(post).getTitle());
            myViewHolder2.t4.setText(homeListBeans.get(post).getNiceDate());

            if (has) {
                myViewHolder2.img.setChecked(true);
            } else {
                myViewHolder2.img.setChecked(false);
            }
            if (myViewHolder2.img.isChecked()) {
                myViewHolder2.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            List<DaoBean> querywhere = utilsDao.querywhere(daoBean);
                            utilsDao.delteone(querywhere);
                            notifyDataSetChanged();
                    }
                });
            } else {
                myViewHolder2.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            utilsDao.insert(daoBean);
                            notifyDataSetChanged();
                    }
                });
            }
            if (homeListBeans.get(post).isFresh()) {
                myViewHolder2.t5.setVisibility(View.VISIBLE);
            } else {
                myViewHolder2.t5.setVisibility(View.GONE);
            }

            TextView t6 = myViewHolder2.t6;
          if(homeListBeans.get(i).getTags().size()>0&&homeListBeans.get(i).getTags()!=null){
              t6.setText(homeListBeans.get(i).getTags().get(0).getName());

          }

            myViewHolder2.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (banners.size() > 0) {
            return homeListBeans.size() + 1;
        } else {
            return homeListBeans.size();
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final Banner myBanner;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myBanner = itemView.findViewById(R.id.myBanner);
        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {

        private final TextView t1;
        private final TextView t2;
        private final TextView t3;
        private final TextView t4;
        private final TextView t5;
        private final TextView t6;
        private final CheckBox img;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);
            t4 = itemView.findViewById(R.id.t4);
            t5 = itemView.findViewById(R.id.t5);
            t6 = itemView.findViewById(R.id.t6);
            img = itemView.findViewById(R.id.img2);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
}
