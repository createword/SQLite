package com.sql.wind.sqldata.sqlLite;

import android.database.sqlite.SQLiteDatabase;

import com.sql.wind.sqldata.general.DataListener;
import com.sql.wind.sqldata.model.ArticleModel;

import java.util.List;

/**
 * Created by WINTER on 2017/1/13.
 * 3执行命令
 */

public abstract class Command<T> {

    public DataListener mDataListener;

    public Command() {

    }

    public Command(DataListener mDataListener) {
        this.mDataListener = mDataListener;
    }

    protected abstract T doBackgrond(SQLiteDatabase sqLiteDatabase);

    public final T execute() {
        SQLiteDatabase database = DataManager.getDatabase();
       //开启事物
        DataManager.beginTransaction();
        T result = doBackgrond(database);
        DataManager.setTransactionSuccess();
        DataManager.endTransaction();
        database.releaseReference();
        return result;
    }

    public static abstract class NoReturnCmd extends Command<Void> {

    }

    public static abstract class ArticlesCommand extends Command<List<ArticleModel>> {
    }
}
