package com.sql.wind.sqldata.sqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by WINTER on 2017/1/13.
 * 1
 */

public class DataHelper extends SQLiteOpenHelper {
    private static final int db_Version = 1;
    private static final String db_Name = "dbName.db";
    public DataHelper(Context context) {
        super(context, db_Name, null, db_Version);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ARTICLES_TABLE_SQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + Tab_ArticleName);
        onCreate(db);
    }

    public static String Tab_ArticleName = "articles";
    private static final String CREATE_ARTICLES_TABLE_SQL = "CREATE TABLE articles (  "
            + " post_id INTEGER PRIMARY KEY , "
            + " userName VARCHAR(30) NOT NULL ,"
            + " title VARCHAR(50) NOT NULL,"
            + " content VARCHAR(50) "
            + " )";

}
