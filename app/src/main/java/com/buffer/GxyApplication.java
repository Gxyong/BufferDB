package com.buffer;

import android.app.Application;

import com.bufferdb.BufferDB;

/**
 * @Author:
 * 时间:
 * 版本:
 * 描述: dec
 * 修改说明:
 */
public class GxyApplication extends Application {

    private static GxyApplication application;
    @Override
    public void onCreate() {
        application=this;
        super.onCreate();
    }


    public static GxyApplication get() {
        return application;
    }

    public void closeDatabase() {
        BufferDB.get(this).closeDatabase();
    }
}
