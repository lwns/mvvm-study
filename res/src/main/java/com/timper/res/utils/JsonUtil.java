package com.timper.res.utils;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by acer on 2017/3/10.
 */

public class JsonUtil {

    public static String mapToJson(Map<String, String> map) {
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }

    private static Gson gson = null;

    static {
        if (gson == null) {
            GsonBuilder builder = new GsonBuilder();
            builder.serializeSpecialFloatingPointValues();
            gson = builder.create();
        }
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String GsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成list
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
        List<T> lst = new ArrayList<>();
        try {
            JsonArray array = new JsonParser().parse(gsonString).getAsJsonArray();
            for (final JsonElement elem : array) {
                lst.add(new Gson().fromJson(elem, cls));
            }
        } catch (Exception e) {
        }
        return lst;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    public static <T> Map<String, T> GsontoMapBean(String gson, Class<T> cls) {
        Map<String, T> map = new HashMap<>();
        try {
            JSONObject mapJson = new JSONObject(gson);
            Iterator<String> keys = mapJson.keys();
            if (keys != null) {
                while (keys.hasNext()) {
                    String key = keys.next();
                    if (TextUtils.isEmpty(key)) continue;
                    map.put(key, JsonUtil.GsonToBean(mapJson.optString(key), cls));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static <T> Map<String, List<T>> GsontoMapListBean(String gson, Class<T> cls) {
        Map<String, List<T>> map = new HashMap<>();
        try {
            JSONObject mapJson = new JSONObject(gson);
            Iterator<String> keys = mapJson.keys();
            if (keys != null) {
                while (keys.hasNext()) {
                    String key = keys.next();
                    if (TextUtils.isEmpty(key)) continue;
                    map.put(key, JsonUtil.GsonToList(mapJson.optString(key), cls));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }
}
