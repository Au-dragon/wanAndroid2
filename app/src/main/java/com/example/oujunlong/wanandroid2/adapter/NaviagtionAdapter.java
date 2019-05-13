package com.example.oujunlong.wanandroid2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.activity.ShowActivity;
import com.example.oujunlong.wanandroid2.bean.NavigationBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class NaviagtionAdapter extends RecyclerView.Adapter {
    private ArrayList<NavigationBean.DataBean> list;
    private Context context;
    private List<NavigationBean.DataBean.ArticlesBean> articles=new ArrayList<>();

    public NaviagtionAdapter(ArrayList<NavigationBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<NavigationBean.DataBean> list1) {
        list.clear();
//        this.list = list1;
        list.addAll(list1);
        notifyDataSetChanged();
    }

    public void setArticles(List<NavigationBean.DataBean.ArticlesBean> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_naviagtion, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
MyViewHolder myViewHolder= (MyViewHolder) viewHolder;
myViewHolder.tv.setText(list.get(i).getName());
        ArrayList<String> title = new ArrayList<>();

        for (int j = 0; j < list.get(i).getArticles().size(); j++) {
            String title1 = list.get(i).getArticles().get(j).getTitle();
            articles.addAll(list.get(i).getArticles());
            title.add(title1);
        }
        if (title != null) {
            myViewHolder.tabflowlayout.setAdapter(new TagAdapter<String>(title) {

                public Button b1;

                @SuppressLint("ResourceAsColor")
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    int[] array = {R.color.Amber, R.color.arrow_color, R.color.colorPrimary
                            , R.color.colorPrimaryDark, R.color.Blue, R.color.color_title_bg, R.color.Green
                            , R.color.Deep_Orange, R.color.Lime, R.color.Teal, R.color.Indigo, R.color.Pink
                            , R.color.Yellow, R.color.Amber, R.color.Purple, R.color.Light_Green
                            , R.color.Light_Blue};

                    int random = (int) (Math.random() * (array.length - 1));
                    View inflate = LayoutInflater.from(context).inflate(R.layout.item_navigation_tag, parent, false);
                    b1 = inflate.findViewById(R.id.b1);
                    b1.setText(s);
                    b1.setTextColor(context.getResources().getColor(array[random]));
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, ShowActivity.class);

                                intent.putExtra("Link",articles.get(i).getApkLink());
                                intent.putExtra("Author",articles.get(i).getAuthor());
                                intent.putExtra("superName",articles.get(i).getSuperChapterName() + "/" + articles.get(i).getChapterName());
                                intent.putExtra("Title",articles.get(i).getTitle());
                                intent.putExtra("NiceDate",articles.get(i).getNiceDate());

                            context.startActivity(intent);
                        }
                    });
                    return inflate;

                }
            });

        }



    }

    @Override
    public int getItemCount() {

        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {


        private final TextView tv;
        private final TagFlowLayout tabflowlayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            tabflowlayout = itemView.findViewById(R.id.flowLayout);
        }
    }
}
