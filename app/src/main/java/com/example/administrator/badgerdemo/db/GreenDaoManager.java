package com.example.administrator.badgerdemo.db;

import android.content.Context;

import com.example.administrator.badgerdemo.BuildConfig;
import com.example.administrator.badgerdemo.MyApplication;
import com.example.administrator.badgerdemo.bean.DaoMaster;
import com.example.administrator.badgerdemo.bean.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

public class GreenDaoManager {
    // 数据库名称
    private static final String DB_NAME="lh_test";
    private static GreenDaoManager mInstance;
    // DaoMaster
    private DaoMaster daoMaster;
    // DaoSession
    private DaoSession daoSession;
    // 开启日志输出

    public static GreenDaoManager getInstance(){
        if(mInstance==null){
            synchronized (GreenDaoManager.class){
                if(mInstance==null){
                    mInstance =new GreenDaoManager(MyApplication.getInstance());
                }
            }
        }
        return mInstance;
    }
    private GreenDaoManager(Context context){
        if(mInstance==null){
            MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context,DB_NAME);
            //DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
            daoSession = daoMaster.newSession();
            // 开启日志输出
            if(BuildConfig.DEBUG){
                QueryBuilder.LOG_SQL = true;
                QueryBuilder.LOG_VALUES = true;
            }else{
                QueryBuilder.LOG_SQL = false;
                QueryBuilder.LOG_VALUES = false;
            }
        }
    }
    public DaoSession getDaoSession(){
        return daoSession;
    }
    public DaoMaster getDaoMaster(){
        return daoMaster;
    }
}
