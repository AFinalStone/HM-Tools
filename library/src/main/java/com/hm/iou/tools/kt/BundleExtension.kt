package com.hm.iou.tools.kt

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 * @author hjy
 * 便捷的包装Bundle的方法
 */

fun Bundle.put(params: Array<out Pair<String, Any?>>?): Bundle {
    params?.forEach {
        val key = it.first
        val value = it.second
        when (value) {
            null -> Unit
            is Byte -> putByte(key, value)
            is ByteArray -> putByteArray(key, value)
            is Boolean -> putBoolean(key, value)
            is BooleanArray -> putBooleanArray(key, value)
            is Char -> putChar(key, value)
            is CharArray -> putCharArray(key, value)
            is Short -> putShort(key, value)
            is ShortArray -> putShortArray(key, value)
            is Int -> putInt(key, value)
            is IntArray -> putIntArray(key, value)
            is Long -> putLong(key, value)
            is LongArray -> putLongArray(key, value)
            is Float -> putFloat(key, value)
            is FloatArray -> putFloatArray(key, value)
            is Double -> putDouble(key, value)
            is DoubleArray -> putDoubleArray(key, value)
            is String -> putString(key, value)
            is Serializable -> putSerializable(key, value)
            is Parcelable -> putParcelable(key, value)
            is CharSequence -> putCharSequence(key, value)
            is Bundle -> putAll(value)
            is Array<*> -> when {
                value.isArrayOf<Parcelable>() -> putParcelableArray(key, value as? Array<out Parcelable>)
                value.isArrayOf<String>() -> putStringArray(key, value as? Array<out String>)
                value.isArrayOf<CharSequence>() -> putCharSequenceArray(key, value as? Array<out CharSequence>)
            }
            is ArrayList<*> -> {
                if (value.size > 0) {
                    when (value.get(0)) {
                        is String -> putStringArrayList(key, value as? ArrayList<String>)
                        is Int -> putIntegerArrayList(key, value as? ArrayList<Int>)
                        is CharSequence -> putCharSequenceArrayList(key, value as? ArrayList<CharSequence>)
                    }
                }
            }
        }
    }
    return this
}

/**
 * @author syl
 * 简化bundle的传值方式
 */
fun Bundle.putValue(key: String?, value: Any?): Bundle {
    when (value) {
        null -> Unit
        is Byte -> putByte(key, value)
        is ByteArray -> putByteArray(key, value)
        is Boolean -> putBoolean(key, value)
        is BooleanArray -> putBooleanArray(key, value)
        is Char -> putChar(key, value)
        is CharArray -> putCharArray(key, value)
        is Short -> putShort(key, value)
        is ShortArray -> putShortArray(key, value)
        is Int -> putInt(key, value)
        is IntArray -> putIntArray(key, value)
        is Long -> putLong(key, value)
        is LongArray -> putLongArray(key, value)
        is Float -> putFloat(key, value)
        is FloatArray -> putFloatArray(key, value)
        is Double -> putDouble(key, value)
        is DoubleArray -> putDoubleArray(key, value)
        is String -> putString(key, value)
        is Serializable -> putSerializable(key, value)
        is Parcelable -> putParcelable(key, value)
        is CharSequence -> putCharSequence(key, value)
        is Bundle -> putAll(value)
        is Array<*> -> when {
            value.isArrayOf<Parcelable>() -> putParcelableArray(key, value as? Array<out Parcelable>)
            value.isArrayOf<String>() -> putStringArray(key, value as? Array<out String>)
            value.isArrayOf<CharSequence>() -> putCharSequenceArray(key, value as? Array<out CharSequence>)
        }
        is ArrayList<*> -> {
            if (value.size > 0) {
                when (value.get(0)) {
                    is String -> putStringArrayList(key, value as? ArrayList<String>)
                    is Int -> putIntegerArrayList(key, value as? ArrayList<Int>)
                    is CharSequence -> putCharSequenceArrayList(key, value as? ArrayList<CharSequence>)
                }
            }
        }
    }
    return this
}

/**
 * 简化Bundle获取参数的方式
 */
inline fun <reified T> Bundle.getValue(key: String): T? = get(key) as? T
