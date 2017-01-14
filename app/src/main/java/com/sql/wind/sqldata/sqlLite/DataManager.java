package com.sql.wind.sqldata.sqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by WINTER on 2017/1/13.
 * 2
 */

public class DataManager {
    private static DataHelper mDataHelper;
    private static SQLiteDatabase sqLiteDatabase;
    private static AtomicInteger sDbRef = new AtomicInteger(0);

    public static void init(Context mContext) {
        if (mContext != null && mDataHelper == null) {

            mDataHelper = new DataHelper(mContext.getApplicationContext());
            sqLiteDatabase = mDataHelper.getWritableDatabase();
        }
    }

    public static void releaseDatabase() {
        if (sDbRef.decrementAndGet() == 0) {//获取当前的值，并自减
            closeDatabase();
        }
    }

    public static void closeDatabase() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
            sqLiteDatabase = null;
        }
    }

    public static SQLiteDatabase getDatabase() {
        if (!sqLiteDatabase.isOpen()) {
            sqLiteDatabase = mDataHelper.getWritableDatabase();
        }
        sDbRef.incrementAndGet();
        return sqLiteDatabase;
    }

    public static void beginTransaction() {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.beginTransaction();
        }
    }

    public static void setTransactionSuccess() {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.setTransactionSuccessful();
        }
    }

    public static void endTransaction() {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.endTransaction();
        }
    }
}
