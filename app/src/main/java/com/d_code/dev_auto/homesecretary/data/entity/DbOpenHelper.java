package com.d_code.dev_auto.homesecretary.data.entity;

import android.content.Context;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

import static com.d_code.dev_auto.homesecretary.data.entity.DaoMaster.dropAllTables;

/**
 * Created by mcauto on 2017. 8. 31..
 */

public class DbOpenHelper extends DaoMaster.OpenHelper {

    public DbOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        Log.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion);

        dropAllTables(db, true);
        onCreate(db);
    }
}