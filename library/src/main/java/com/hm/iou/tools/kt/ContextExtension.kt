package com.hm.iou.tools.kt

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.io.Serializable

/**
 * @author hjy
 *
 * Context的一些扩展方法或属性
 */

/**
 * Kt里启动 Activity 的简写方式
 */
inline fun <reified T : Activity> Context.startActivity() {
    startActivity<T>(null)
}

/**
 * Kt里启动 Activity 的简写方式
 */
inline fun <reified T : Activity> Context.startActivity(extras: Bundle?) {
    val intent = Intent(this, T::class.java)
    if (this !is Activity) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    extras?.let {
        intent.putExtras(extras)
    }
    startActivity(intent)
}

/**
 * Kt里启动 Activity 的简写方式
 */
inline fun <reified T : Activity> Context.startActivity(vararg extras: Pair<String, Any>) {
    val bundle = Bundle()
    bundle.put(extras)
    startActivity<T>(bundle)
}

/**
 * Kt里启动 Activity 的简写方式
 */
inline fun <reified T : Activity> Activity.startActivityForResult(reqCode: Int) {
    startActivityForResult<T>(reqCode, null)
}

/**
 * Kt里启动 Activity 的简写方式
 */
inline fun <reified T : Activity> Activity.startActivityForResult(reqCode: Int, extras: Bundle?) {
    val intent = Intent(this, T::class.java)
    extras?.let {
        intent.putExtras(extras)
    }
    startActivityForResult(intent, reqCode)
}

/**
 * Kt里启动 Activity 的简写方式
 */
inline fun <reified T : Activity> Activity.startActivityForResult(reqCode: Int, vararg extras: Pair<String, Any?>) {
    val bundle = Bundle()
    bundle.put(extras)
    startActivityForResult<T>(reqCode, bundle)
}

/**
 * 屏幕宽度(px)
 */
inline val Context.screenWidth: Int
    get() = resources.displayMetrics.widthPixels

/**
 * 屏幕高度(px)
 */
inline val Context.screenHeight: Int
    get() = resources.displayMetrics.heightPixels

/**
 * 屏幕的密度
 */
inline val Context.density: Float
    get() = resources.displayMetrics.density

inline val Context.scaledDensity: Float
    get() = resources.displayMetrics.scaledDensity

/**
 * dp 转为 px
 */
inline fun Context.dp2px(value: Int): Int = (density * value).toInt()

/**
 * sp 转为 px
 */
inline fun Context.sp2px(value: Int): Int = (scaledDensity * value).toInt()

/**
 * dp 转为 px
 */
inline fun Context.dp2px(value: Float): Int = (density * value).toInt()

/**
 * sp 转为 px
 */
inline fun Context.sp2px(value: Float): Int = (scaledDensity * value).toInt()

/**
 * px 转为 dp
 */
inline fun Context.px2dp(value: Int): Float = value.toFloat() / density

/**
 * px 转为 sp
 */
inline fun Context.px2sp(value: Int): Float = value.toFloat() / scaledDensity

inline fun Context.inflateLayout(@LayoutRes layoutResId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this).inflate(layoutResId, parent, attachToRoot)
}
