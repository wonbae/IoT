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
    private BeaconManager beaconManager;
    @Override
    public void onCreate() {
        super.onCreate();
        mDaoSession = new DaoMaster(new DbOpenHelper(this, "hs.db").getWritableDb()).newSession();
        beaconManager = new BeaconManager(getApplicationContext());
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {

            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(new Region("monitoring region", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), 40259, 11605));


            }
        });

        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                Log.d("beacon", "get in region" + list.get(0).getRssi());
                if (list.get(0).getRssi() > -70) {
                    beaconManager.stopMonitoring(region);
                }
            }

            @Override
            public void onExitedRegion(Region region) {
                Log.d("beacon", "get out region");
            }
        });
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}

