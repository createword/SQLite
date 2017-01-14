package com.sql.wind.sqldata.sqlLite;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.sql.wind.sqldata.general.DataListener;

/**
 * Created by WINTER on 2017/1/13.
 * 4 执行者
 */

public class dbExecutor {
    private static final HandlerThread HT = new HandlerThread(dbExecutor.class.getName(), android.os.Process.THREAD_PRIORITY_BACKGROUND);

    static {
        HT.start();
    }

    //异步Handler
    private static Handler asyHandler = new Handler(HT.getLooper());
    //主Handler
    private static Handler mainHandler = new Handler(Looper.getMainLooper());
    private static dbExecutor executor = null;



    public static dbExecutor getInstance() {
        if (executor == null) {
            executor = new dbExecutor();
        }
        return executor;
    }

    public <T> void executor(final Command<T> command) {   //执行者拿到执行命令进行操作
        asyHandler.post(new Runnable() {
            @Override
            public void run() {
                T result = command.execute();
                if (command.mDataListener != null) {
                    postResult(result, command.mDataListener);
                }
            }
        });
    }

    //将结果投射到主线程中
    private <T> void postResult(final T result, final DataListener<T> listener) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                listener.onComplete(result);
            }
        });
    }
}
