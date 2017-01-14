package com.sql.wind.sqldata;

import android.app.Application;

import com.sql.wind.sqldata.general.L;
import com.sql.wind.sqldata.sqlLite.DataManager;

/**
 * Created by WINTER on 2017/1/13.
 */

public class DefaultApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.init(this);
       // L.isDebug=true;

    }
}
