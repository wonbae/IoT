package com.d_code.dev_auto.homesecretary;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.d_code.dev_auto.homesecretary.data.entity.CheckList;
import com.d_code.dev_auto.homesecretary.data.entity.Event;

/**
 * Created by mcauto on 2017-08-29.
 */

public class HomeSecretaryApplication extends com.activeandroid.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Configuration.Builder dbConfiguration = new Configuration.Builder(this);
        dbConfiguration.addModelClass(CheckList.class);
        dbConfiguration.addModelClass(Event.class);
        ActiveAndroid.initialize(this);
    }
}
