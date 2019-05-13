package com.example.oujunlong.wanandroid2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.bean.ProjectListBean;
import com.example.oujunlong.wanandroid2.utils.SpUtil;

import java.util.ArrayList;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class ProjectDateAdapter extends RecyclerView.Adapter {
    private ArrayList<ProjectListBean.DataBean.DatasBean> list;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public ProjectDateAdapter(ArrayList<ProjectListBean.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<ProjectListBean.DataBean.DatasBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_projectdate, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyViewHolder myViewHolder= (MyViewHolder) viewHolder;
        myViewHolder.t1.setText(list.get(i).getTitle());
        myViewHolder.t2.setText(list.get(i).getDesc());
        myViewHolder.t3.setText(list.get(i).getNiceDate());
        myViewHolder.t4.setText(list.get(i).getAuthor());
        Boolean wutu = (Boolean) SpUtil.getParam("wutu", false);
        if(wutu){
            myViewHolder.img.setImageResource(R.drawable.app);
        }else {
            Glide.with(context).load(list.get(i).getEnvelopePic()).into(myViewHolder.img);
        }

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView t1;
        private final TextView t2;
        private final TextView t3;
        private final TextView t4;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);
            t4 = itemView.findViewById(R.id.t4);

        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
}
