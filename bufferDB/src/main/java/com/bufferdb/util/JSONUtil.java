package com.bufferdb.util;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * Fake fastjson with Gson
 */
public class JSONUtil {
    private static final Gson GSON = new GsonBuilder().
            registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
                @Override
                public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                    if (src == src.longValue())
                        return new JsonPrimitive(src.longValue());
                    return new JsonPrimitive(src);
                }
            })
            .disableHtmlEscaping()
            .create();

    @Nullable
    public static String toJSONString(Object obj) {
        try {
            return GSON.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static <T> T parseObject(String json, Class<T> clazz) {
        try {
            return GSON.fromJson(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static <T> T[] parseArray(String json, Class<T> clazz) {
        List<T> list = parseList(json, clazz);
        if (list != null) {
            T[] tArray = (T[]) Array.newInstance(clazz, list.size());
            return list.toArray(tArray);
        } else {
            return null;
        }

    }

    @Nullable
    public static <T> List<T> parseList(String json, Class<T> clazz) {
        List<T> list = null;
        try {
            Type type = new ListParameterizedTypeImpl(clazz);
            list = GSON.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private static class ListParameterizedTypeImpl implements ParameterizedType {
        private Class clazz;

        private ListParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @NonNull
        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @NonNull
        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}