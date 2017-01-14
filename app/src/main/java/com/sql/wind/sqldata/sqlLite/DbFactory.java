package com.sql.wind.sqldata.sqlLite;

import com.sql.wind.sqldata.modeimp.ModeImp;
import com.sql.wind.sqldata.model.ArticleModel;

public class DbFactory {
    public static AbsDBAPI<ArticleModel> createArticleModel() {
        return new ModeImp();
    }


}
