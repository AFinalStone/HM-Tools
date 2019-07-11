package com.hm.iou.tools.kt

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import kotlin.reflect.KProperty

/**
 * @author hjy
 *
 * 通过委托的方式，获取从 Activity/Fragment 传递的参数
 */
class ExtrasDelegate<out T>(private val extraKey: String, private val defValue: T?) {

    private var extraValue: T? = null

    operator fun getValue(thisRef: Activity, property: KProperty<*>): T? {
        extraValue = extraValue ?: (thisRef.intent?.extras?.get(this.extraKey) as? T?)
        return extraValue ?: this.defValue
    }

    operator fun <R> setValue(thisRef: Activity, property: KProperty<*>, value: R?) {
        extraValue = value as? T
    }

    operator fun getValue(thisRef: Fragment, property: KProperty<*>): T? {
        extraValue = extraValue ?: thisRef.arguments?.get(this.extraKey) as? T?
        return extraValue ?: this.defValue
    }

    operator fun <R> setValue(thisRef: Fragment, property: KProperty<*>, value: R?) {
        extraValue = value as? T
    }

}

/**
 * 通过委托来简化从 Activity/Fragment 里获取参数
 */
inline fun <T> extraDelegate(key: String, defValue: T?) = ExtrasDelegate(key, defValue)


inline fun <T> unPackData(savedInstanceState: Bundle?, key: String): T? = savedInstanceState?.get(key) as? T