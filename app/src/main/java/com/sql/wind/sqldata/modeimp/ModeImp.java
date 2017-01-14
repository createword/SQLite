package com.sql.wind.sqldata.modeimp;

import android.content.ContentValues;
import android.database.Cursor;

import com.sql.wind.sqldata.model.ArticleModel;
import com.sql.wind.sqldata.sqlLite.AbsDBAPI;
import com.sql.wind.sqldata.sqlLite.DataHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WINTER on 2017/1/13.
 * 文章相关数据API
 */

public class ModeImp extends AbsDBAPI<ArticleModel> {

    public ModeImp() {
        super(DataHelper.Tab_ArticleName);
    }

    @Override
    protected String loadDatasOrderBy() {
        return " publish_time DESC";
    }

    @Override
    protected List<ArticleModel> parseResult(Cursor cursor) {
        List<ArticleModel> articles = new ArrayList<ArticleModel>();
        while (cursor.moveToNext()) {
            ArticleModel item = new ArticleModel();
            item.pid = cursor.getInt(0);
            item.userName = cursor.getString(1);
            item.title = cursor.getString(2);
            item.content = cursor.getString(3);
            // 解析数据
            articles.add(item);
        }
        return articles;
    }

    /**
     * @param item
     * @return
     * 这里的键对应着数据库创建的表里的值
     */
    @Override
    protected ContentValues toContentValues(ArticleModel item) {
        ContentValues newValues = new ContentValues();
        newValues.put("post_id", item.pid);
        newValues.put("userName", item.userName);
        newValues.put("title", item.title);
        newValues.put("content", item.content);

        return newValues;
    }
}
