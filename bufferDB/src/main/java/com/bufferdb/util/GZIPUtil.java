package com.bufferdb.util;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * GZIP compress Util
 */

public class GZIPUtil {
    @Nullable
    public static String compress(String data) {
        if (TextUtils.isEmpty(data)) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(data.getBytes());
            gzipOutputStream.close();
            String compressData = byteArrayOutputStream.toString("ISO-8859-1");
            byteArrayOutputStream.close();
            return compressData;
        } catch (Exception ignore) {
            return null;
        }
    }

    @Nullable
    public static String decompress(String data) {
        if (TextUtils.isEmpty(data)) {
            return null;
        }
        try {
            String decompressedStr = "";
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data.getBytes("ISO-8859-1"));
            GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
            byte[] b = new byte[256];
            int length = -1;
            while (-1 != (length = gzipInputStream.read(b))) {
                byteArrayOutputStream.write(b, 0, length);
            }
            decompressedStr = byteArrayOutputStream.toString("UTF-8");
            byteArrayOutputStream.close();
            byteArrayInputStream.close();
            gzipInputStream.close();
            return decompressedStr;
        } catch (Exception e) {
            return null;
        }
    }
}