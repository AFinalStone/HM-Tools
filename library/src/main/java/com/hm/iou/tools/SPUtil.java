package com.hm.iou.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hjy on 18/4/27.<br>
 */

public class SPUtil {

    /**
     * 保存
     *
     * @param context
     * @param fileName SharedPreference文件名
     * @param key
     * @param value 支持String, int, boolean, float, long类型
     * @return
     */
    public static boolean put(Context context, String fileName, String key, Object value) {
        SharedPreferences sp = getSp(context, fileName);
        SharedPreferences.Editor editor = sp.edit();
        if(value == null) {
            editor.putString(key, null);
        } else if(value instanceof String) {
            editor.putString(key, (String) value);
        } else if(value instanceof Integer) {
            editor.putInt(key, (Integer)value);
        } else if(value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if(value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if(value instanceof Long) {
            editor.putLong(key, (Long) value);
        }
        return editor.commit();
    }

    public static String getString(Context context, String fileName, String key, String defValue) {
        SharedPreferences sp = getSp(context, fileName);
        return sp.getString(key, defValue);
    }

    public static String getString(Context context, String fileName, String key) {
        return getString(context, fileName, key, null);
    }

    public static int getInt(Context context, String fileName, String key, int defValue) {
        SharedPreferences sp = getSp(context, fileName);
        return sp.getInt(key, defValue);
    }

    public static int getInt(Context context, String fileName, String key) {
        return getInt(context, fileName, key, 0);
    }

    public static boolean getBoolean(Context context, String fileName, String key, boolean defValue) {
        SharedPreferences sp = getSp(context, fileName);
        return sp.getBoolean(key, defValue);
    }

    public static boolean getBoolean(Context context, String fileName, String key) {
        return getBoolean(context, fileName, key, false);
    }

    public static float getFloat(Context context, String fileName, String key, float defValue) {
        SharedPreferences sp = getSp(context, fileName);
        return sp.getFloat(key, defValue);
    }

    public static float getFloat(Context context, String fileName, String key) {
        return getFloat(context, fileName, key, 0f);
    }

    public static long getLong(Context context, String fileName, String key, long defValue) {
        SharedPreferences sp = getSp(context, fileName);
        return sp.getLong(key, defValue);
    }

    public static long getLong(Context context, String fileName, String key) {
        return getLong(context, fileName, key, 0);
    }

    public static boolean remove(Context context, String fileName, String key) {
        SharedPreferences sp = getSp(context, fileName);
        return sp.edit().remove(key).commit();
    }

    public static boolean clear(Context context, String fileName) {
        SharedPreferences sp = getSp(context, fileName);
        return sp.edit().clear().commit();
    }

    private static SharedPreferences getSp(Context context, String fileName) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp;
    }

}
