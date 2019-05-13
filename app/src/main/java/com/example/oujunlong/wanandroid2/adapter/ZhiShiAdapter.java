package com.example.oujunlong.wanandroid2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.bean.ZhishiBean;

import java.util.ArrayList;

/**
 * creation time 2019/5/5
 * author oujunlong
 */
public class ZhiShiAdapter  extends RecyclerView.Adapter {
    private ArrayList<ZhishiBean.DataBean> list;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public ZhiShiAdapter(ArrayList<ZhishiBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<ZhishiBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_zhishi_adapter, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
MyViewHolder myViewHolder= (MyViewHolder) viewHolder;
myViewHolder.t1.setText(list.get(i).getName());
StringBuffer string=new StringBuffer();
        for (int j = 0; j < list.get(i).getChildren().size(); j++) {
            string.append(list.get(i).getChildren().get(j).getName()+" ");
        }
        myViewHolder.t2.setText(string);
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

        private final TextView t1;
        private final TextView t2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
}
