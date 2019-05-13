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
import com.example.oujunlong.wanandroid2.bean.ZhiShiDateBean;
import com.example.oujunlong.wanandroid2.utils.UtilsDao;

import java.util.ArrayList;
import java.util.List;

/**
 * creation time 2019/5/6
 * author oujunlong
 */
public class ZhiShiDateRvAdapter extends RecyclerView.Adapter {
    private ArrayList<ZhiShiDateBean.DataBean.DatasBean> list;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public ZhiShiDateRvAdapter(ArrayList<ZhiShiDateBean.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<ZhiShiDateBean.DataBean.DatasBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_homelist, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyViewHolder myViewHolder= (MyViewHolder) viewHolder;
        final UtilsDao utilsDao = UtilsDao.getUtilsDao();

        boolean has=false;
        final DaoBean   daoBean = new DaoBean(null, list.get(i).getAuthor()
                , list.get(i).getSuperChapterName() + "/" + list.get(i).getChapterName()
                , list.get(i).getTitle()
                , list.get(i).getNiceDate());
        has = utilsDao.has(daoBean);


        myViewHolder.t1.setText(list.get(i).getAuthor());
        myViewHolder.t2.setText(list.get(i).getSuperChapterName() + "/" + list.get(i).getChapterName());
        myViewHolder.t3.setText(list.get(i).getTitle());
        myViewHolder.t4.setText(list.get(i).getNiceDate());

        if (has) {
            myViewHolder.img.setChecked(true);
        } else {
            myViewHolder.img.setChecked(false);
        }
        if (myViewHolder.img.isChecked()) {
            myViewHolder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<DaoBean> querywhere = utilsDao.querywhere(daoBean);
                    utilsDao.delteone(querywhere);
                    notifyDataSetChanged();
                }
            });
        } else {
            myViewHolder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    utilsDao.insert(daoBean);
                    notifyDataSetChanged();
                }
            });
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

        private final TextView t1;
        private final TextView t2;
        private final TextView t3;
        private final TextView t4;
        private final CheckBox img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);
            t4 = itemView.findViewById(R.id.t4);
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
