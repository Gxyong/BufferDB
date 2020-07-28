
package com.bufferdb;

import android.util.LruCache;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.Set;

/**
 * Created by LiShen on 2017/11/21.
 * Lru cache map
 */

@SuppressWarnings("unchecked")
public class BufferDBMemoryCache {
    private static final String PREFIX_STRONG = "strong:";
    private static final String PREFIX_WEAK = "weak:";

    private volatile LruCache<String, Object> CACHE;

    public BufferDBMemoryCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        CACHE = new LruCache<>(maxSize);
    }

    public synchronized void put(String key, Object value) {
        put(key, value, false);
    }

    public synchronized void put(String key, Object value, boolean strong) {
        if (strong) {
            CACHE.put(PREFIX_STRONG + key, value);
        } else {
            CACHE.put(PREFIX_WEAK + key, new WeakReference<>(value));
        }
    }

    @Nullable
    public synchronized <T> T get(String key) {
        return get(key, false);
    }

    @Nullable
    public synchronized <T> T get(String key, boolean strong) {
        T value = null;
        if (strong) {
            try {
                value = (T) CACHE.get(PREFIX_STRONG + key);
                if (value instanceof WeakReference) {
                    throw new RuntimeException("Maybe use wrong STRONG type");
                }
            } catch (Exception e) {
                e.printStackTrace();
                value = null;
            }
        } else {
            try {
                WeakReference<Object> valueRef = (WeakReference<Object>) CACHE.get(PREFIX_WEAK + key);
                if (valueRef != null) {
                    value = (T) valueRef.get();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public synchronized void delete(String key) {
        CACHE.remove(PREFIX_STRONG + key);
        CACHE.remove(PREFIX_WEAK + key);
    }

    public synchronized void clear() {
        CACHE.evictAll();
    }

    public synchronized int size() {
        return CACHE.size();
    }

    public synchronized String[] keySet() {
        Set<String> keySet = CACHE.snapshot().keySet();
        return keySet.toArray(new String[0]);
    }
}