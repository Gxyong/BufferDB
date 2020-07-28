package com.bufferdb;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * BufferDB init module
 * <p>
 * To use this interface:
 * <p>
 * Implement the BufferDBModule interface in a class with public visibility
 * <p>
 * public class YourBufferDBModule implements BufferDBModule {
 * {@literal @}Override
 * public BufferDB applyConfiguration(Context context) {
 * ....
 * }
 * }
 * <p>
 * Add your implementation to your list of keeps in your proguard.cfg file:
 * <p>
 * {@code
 * -keepnames class * com.xxx.xxx.xxx.xxx.YourBufferDBModule
 * }
 * <p>
 * Add a metadata tag to your AndroidManifest.xml with your BufferDBModule implementation's
 * <p>
 * <p>
 * {@code
 * <meta-data
 * android:name="icom.bufferdb.BufferDBModule"
 * android:value="com.xxx.xxx.xxx.xxx.YourBufferDBModule" />
 * }
 */

public interface BufferDBModule {
    /**
     * Get a configuration for BufferDB init, do not be null
     *
     * @return configuration
     */
    @NonNull
    BufferDBConfiguration applyConfiguration(Context context);
}