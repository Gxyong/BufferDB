package com.buffer;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;

import com.bufferdb.BufferDBConfiguration;
import com.bufferdb.BufferDBModule;

import java.io.File;

/**
 * @Author: 文西
 * 时间:     2020/7/28$ 20:39$
 * 版本:
 * 描述: dec
 * 修改说明:
 */
public class DemoBufferDBModule implements BufferDBModule {
    @NonNull
    @Override
    public BufferDBConfiguration applyConfiguration(Context context) {

        File databaseFolder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            databaseFolder = context.getExternalFilesDirs(null)[0];
        } else {
            databaseFolder = context.getExternalFilesDir(null);
        }
        if (databaseFolder != null && databaseFolder.exists()) {
            String path = databaseFolder.getAbsolutePath();
            if (!path.endsWith(File.separator)) {
                path = path + File.separator;
            }
            path = path + "database/";
            databaseFolder = new File(path);
        }
        return new BufferDBConfiguration.Builder(context)
                .databaseFolder(databaseFolder)
                .logEnabled(true)
                .databaseName("BufferDBDemo")
                .build();
    }
}
