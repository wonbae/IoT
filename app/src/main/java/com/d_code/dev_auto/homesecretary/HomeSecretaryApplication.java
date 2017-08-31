package com.d_code.dev_auto.homesecretary;

import android.app.Application;

import com.d_code.dev_auto.homesecretary.data.entity.DaoMaster;
import com.d_code.dev_auto.homesecretary.data.entity.DaoSession;
import com.d_code.dev_auto.homesecretary.data.entity.DbOpenHelper;

/**
 * Created by mcauto on 2017-08-29.
 */

public class HomeSecretaryApplication extends Application {
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mDaoSession = new DaoMaster(new DbOpenHelper(this, "hs.db").getWritableDb()).newSession();

//        if(mDaoSession.getCheckListDao().loadAll().size() == 0){
//            mDaoSession.getCheckListDao().insert(new CheckList());
//        }
    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
