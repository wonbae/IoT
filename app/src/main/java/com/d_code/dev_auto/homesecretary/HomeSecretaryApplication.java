package com.d_code.dev_auto.homesecretary;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.d_code.dev_auto.homesecretary.data.entity.DaoMaster;
import com.d_code.dev_auto.homesecretary.data.entity.DaoSession;
import com.d_code.dev_auto.homesecretary.data.entity.DbOpenHelper;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;
import java.util.UUID;

/**
 * Created by mcauto on 2017-08-29.
 */

public class HomeSecretaryApplication extends Application {
    private DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        mDaoSession = new DaoMaster(new DbOpenHelper(this, "hs.db").getWritableDb()).newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}

