package com.example.administrator.badgerdemo.db;

import android.content.Context;

import com.example.administrator.badgerdemo.bean.DaoMaster;
import com.example.administrator.badgerdemo.bean.UserInfo;
import com.example.administrator.badgerdemo.bean.UserInfoDao;

import org.greenrobot.greendao.database.Database;

public class MySQLiteOpenHelper extends  DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name) {
        super(context, name);
    }
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //super.onUpgrade(db, oldVersion, newVersion);
        //将db传入, 将目录下的所有的Dao.类传入
        MigrationHelper.migrate(db, UserInfoDao.class);
    }
}
