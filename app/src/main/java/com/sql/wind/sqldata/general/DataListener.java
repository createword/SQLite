package com.sql.wind.sqldata.general;

/**
 * Created by WINTER on 2017/1/13.
 */

public interface DataListener<T> {
    public void onComplete(T result);
}
