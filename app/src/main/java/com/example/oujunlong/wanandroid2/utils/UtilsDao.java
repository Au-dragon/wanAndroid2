package com.example.oujunlong.wanandroid2.utils;

import android.widget.Toast;

import com.example.oujunlong.wanandroid2.app.MyApp;
import com.example.oujunlong.wanandroid2.bean.DaoBean;
import com.example.xts.greendaodemo.db.DaoBeanDao;
import com.example.xts.greendaodemo.db.DaoMaster;

import java.util.List;

/**
 * creation time 2019/5/9
 * author oujunlong
 */
public class UtilsDao {
    private static UtilsDao utilsDao;
        private final DaoBeanDao daoBeanDao;

        private UtilsDao() {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApp.getMyApp(), "info.db");
            DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            daoBeanDao = daoMaster.newSession().getDaoBeanDao();

        }

        public static UtilsDao getUtilsDao() {
            if (utilsDao == null) {
                synchronized (UtilsDao.class) {
                    if (utilsDao == null) {
                        utilsDao = new UtilsDao();
                    }
                }
            }
            return utilsDao;
        }
        public boolean  has(DaoBean daoBean){
            List<DaoBean> list = daoBeanDao.queryBuilder().where(DaoBeanDao.Properties.Title.eq(daoBean.getTitle())).list();
            if(list.size()>0&&list!=null){
                return true;
            }else {
                return false;
            }
        }
        public void insert(DaoBean daoBean){
            if(has(daoBean)){
                return;
            }else {
                daoBeanDao.insert(daoBean);
                Toast.makeText(MyApp.getMyApp(), "收藏成功", Toast.LENGTH_SHORT).show();
            }
        }
        public void delte(DaoBean daoBean){
            if(has(daoBean)&&daoBean!=null){
                daoBeanDao.delete(daoBean);
                Toast.makeText(MyApp.getMyApp(), "取消收藏成功", Toast.LENGTH_SHORT).show();
            }else {
                return;
            }
        }
        public void delteone(List<DaoBean> list){

            if(list.size()>0){
                daoBeanDao.deleteInTx(list);
                Toast.makeText(MyApp.getMyApp(), "取消收藏成功", Toast.LENGTH_SHORT).show();
            }
        }

        public List<DaoBean> querywhere(DaoBean daoBean){
            return daoBeanDao.queryBuilder().where(DaoBeanDao.Properties.Title.eq(daoBean.getTitle())).list();
        }
        public List<DaoBean> query(){
            return daoBeanDao.loadAll();
        }
        public void updata(DaoBean daoBean){
            daoBeanDao.update(daoBean);
        }
}
