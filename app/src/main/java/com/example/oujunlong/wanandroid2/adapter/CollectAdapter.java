package com.example.oujunlong.wanandroid2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.oujunlong.wanandroid2.R;
import com.example.oujunlong.wanandroid2.app.MyApp;
import com.example.oujunlong.wanandroid2.bean.DaoBean;
import com.example.oujunlong.wanandroid2.utils.UtilsDao;

import java.util.ArrayList;

/**
 * creation time 2019/5/9
 * author oujunlong
 */
public class CollectAdapter extends RecyclerView.Adapter {
    private ArrayList<DaoBean> list = new ArrayList<>();
    private Context context;

    public CollectAdapter(ArrayList<DaoBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<DaoBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_homelist, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.t1.setText(list.get(i).getAuthor());
        myViewHolder.t2.setText(list.get(i).getSuperChapterName());
        myViewHolder.t3.setText(list.get(i).getTitle());
        myViewHolder.t4.setText(list.get(i).getNiceDate());
        myViewHolder.img.setChecked(true);
        myViewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    UtilsDao.getUtilsDao().delte(list.get(i));
                    list.remove(i);
                notifyDataSetChanged();
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
        private final TextView t3;
        private final TextView t4;
        private final TextView t5;
        private final CheckBox img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);
            t4 = itemView.findViewById(R.id.t4);
            t5 = itemView.findViewById(R.id.t5);
            img = itemView.findViewById(R.id.img2);
        }
    }
}
