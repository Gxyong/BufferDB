package com.bufferdb;

import android.content.Context;
import android.text.TextUtils;

import androidx.core.content.ContextCompat;

import java.io.File;


/**
 * BufferDB init configuration
 */

public class BufferDBConfiguration {
    Context context;
    String databaseName;
    File databaseFolder;
    int memoryCacheSize;
    boolean logEnabled;

    private BufferDBConfiguration(Builder builder) {
        context = builder.context;
        databaseName = builder.databaseName;
        databaseFolder = builder.databaseFolder;
        memoryCacheSize = builder.memoryCacheSize;
        logEnabled = builder.logEnabled;

        // application context
        if (context == null) {
            throw new IllegalArgumentException("Context can not be null!");
        }
        // default core name
        if (TextUtils.isEmpty(databaseName)) {
            databaseName = context.getPackageName();
        }
        // core folder
        boolean databaseFolderValid = false;
        if (databaseFolder != null) {
            boolean databaseFolderExist = false;
            try {
                databaseFolderExist = databaseFolder.exists();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!databaseFolderExist) {
                try {
                    databaseFolderValid = databaseFolder.mkdirs();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                databaseFolderValid = true;
            }
        }
        if (!databaseFolderValid) {
            File[] folders = ContextCompat.getExternalFilesDirs(context, null);
            if (folders.length == 0) {
                databaseFolder = context.getFilesDir();
            } else {
                databaseFolder = folders[0];
            }
        }
        if (databaseFolder == null) {
            throw new IllegalArgumentException("Database folder can not be null!");
        }
        // memory cache max size
        if (memoryCacheSize <= 0) {
            memoryCacheSize = BufferDB.DEFAULT_MEMORY_CACHE_SIZE;
        }
    }

    public static final class Builder {
        private Context context;
        private String databaseName;
        private File databaseFolder;
        private int memoryCacheSize;
        private boolean logEnabled;

        public Builder(Context context) {
            this.context = context.getApplicationContext();
        }

        /**
         * Database name
         *
         * @param val name
         * @return
         */
        public Builder databaseName(String val) {
            databaseName = val;
            return this;
        }

        /**
         * Database will in this folder
         *
         * @param val folder
         * @return
         */
        public Builder databaseFolder(File val) {
            databaseFolder = val;
            return this;
        }

        /**
         * Memory cache max size
         *
         * @param val size
         * @return
         */
        public Builder memoryCacheSize(int val) {
            memoryCacheSize = val;
            return this;
        }

        /**
         * Log
         *
         * @param val enabled
         * @return
         */
        public Builder logEnabled(boolean val) {
            logEnabled = val;
            return this;
        }

        public BufferDBConfiguration build() {
            return new BufferDBConfiguration(this);
        }
    }
}