
package com.sql.wind.sqldata.sqlLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.sql.wind.sqldata.general.DataListener;
import com.sql.wind.sqldata.general.L;

import java.util.List;

/**
 * @param <T>
 * @author mrsimple
 */
public abstract class AbsDBAPI<T> {
    /**
     * 数据库执行引擎  增删查改  通过执行者命令模式
     */
    protected dbExecutor sDbExecutor = dbExecutor.getInstance();
    /**
     * 表名
     */
    protected String mTableName;

    public AbsDBAPI(String table) {
        mTableName = table;
    }

    /**
     * 保存数据
     *
     * @param item
     */
    public void saveItem(final T item) {
        System.out.print(item.toString()+"------------");
        sDbExecutor.executor(new Command.NoReturnCmd() {
            @Override
            protected Void doBackgrond(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.insertWithOnConflict(mTableName, null, toContentValues(item),
                        SQLiteDatabase.CONFLICT_REPLACE);
                return null;
            }
        });
    }

    protected ContentValues toContentValues(T item) {
        return null;
    }

    /**
     * 保存数据到数据库
     *
     * @param
     */
    public void saveItems(List<T> datas) {
        for (T item : datas) {


            Log.d("msg", "-------------------ddd"+item.toString());
            saveItem(item);
        }
    }

    /**
     * 加载所有缓存
     *
     * @param listener
     */
    public void loadDatasFromDB(DataListener<List<T>> listener) {
        sDbExecutor.executor(new Command<List<T>>() {
            @Override
            protected List doBackgrond(SQLiteDatabase sqLiteDatabase) {
                Cursor cursor = sqLiteDatabase.query(mTableName, null, null, null,
                        null, null, loadDatasOrderBy());
                List<T> result = parseResult(cursor);
                cursor.close();
                return result;
            }
        });

    }

    protected String loadDatasOrderBy() {
        return "";
    }

    /**
     * 从Cursor中解析数据
     *
     * @param cursor
     * @return
     */
    protected List<T> parseResult(Cursor cursor) {
        return null;
    }

    /**
     * 删除符合特定条件的数据
     */
    public void deleteWithWhereArgs(final String whereArgs) {

        sDbExecutor.executor(new Command<Void>() {
            @Override
            protected Void doBackgrond(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL("delete from " + mTableName + whereArgs);
                return null;
            }

        });
    }

    public void deleteAll() {
        sDbExecutor.executor(new Command<Void>() {
            @Override
            protected Void doBackgrond(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL("delete from " + mTableName);
                return null;
            }
        });
    }
}
