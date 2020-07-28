package com.bufferdb;


import androidx.annotation.Nullable;

import com.snappydb.DB;
import com.snappydb.DBFactory;

/**
 * BufferDBDatabase
 * Time: 2019/4/3 11:09
 */
class BufferDBDatabase {

    @Nullable
    private DB core = null;

    DB get() {
        return core;
    }

    boolean open(String path, String name) {
        try {
            if (core != null && core.isOpen()) {
                return true;
            }
            synchronized (DB.class) {
                core = DBFactory.open(path, name);
                return true;
            }
        } catch (Exception ignore) {
            return false;
        }
    }

    boolean close() {
        boolean result;
        try {
            if (core != null) {
                core.close();
            }
            result = true;
        } catch (Exception ignore) {
            result = false;
        } finally {
            core = null;
        }
        return result;
    }

    boolean isAvailable() {
        boolean available = false;
        try {
            available = core != null && core.isOpen();
        } catch (Exception ignore) {

        }
        return available;
    }
}