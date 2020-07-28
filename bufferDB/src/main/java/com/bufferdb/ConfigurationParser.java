package com.bufferdb;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;


/**
 * BufferDB Configuration parser
 */

class ConfigurationParser {
    private final Context context;

    ConfigurationParser(Context context) {
        this.context = context.getApplicationContext();
    }

    @NonNull
    BufferDBConfiguration parse() {
        BufferDBConfiguration configuration = null;
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo != null && appInfo.metaData != null) {
                String className = appInfo.metaData.getString(BufferDB.BUFFERDB_MODULE_NAME, "");
                Log.i("bufferDB class", "parse: className="+className);
                configuration = parseModule(className).applyConfiguration(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (configuration == null) {
            configuration = new BufferDBConfiguration.Builder(context).build();
        }
        return configuration;
    }

    private static BufferDBModule parseModule(String className) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to find BufferDBModule implementation", e);
        }
        Object module;
        try {
            module = clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Unable to instantiate BufferDBModule implementation for " + clazz, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to instantiate BufferDBModule implementation for " + clazz, e);
        }
        if (!(module instanceof BufferDBModule)) {
            throw new RuntimeException("Expected instanceof BufferDBModule, but found: " + module);
        }
        return (BufferDBModule) module;
    }
}