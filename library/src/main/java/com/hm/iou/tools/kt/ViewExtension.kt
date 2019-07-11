package com.hm.iou.tools.kt

import android.view.View

/**
 * View的一些扩展方法
 */

/**
 * View的点击事件
 */
fun <T : View> T.click(block: (T) -> Unit) {
    setOnClickListener {
        block(this)
    }
}

/**
 * 延迟点击事件，防止频繁点击
 */
fun <T : View> T.clickWithDuration(time: Long = 500, block: (T) -> Unit) {
    delayTime = time
    setOnClickListener {
        if (canClick()) {
            block(this)
        }
    }
}

fun <T : View> T.longClick(block: (T) -> Boolean) {
    setOnLongClickListener {
        return@setOnLongClickListener block(this)
    }
}

/**
 * 是否可见
 */
fun <T : View> T.isVisible(): Boolean = this.visibility == View.VISIBLE

/**
 * 是否不可见
 */
fun <T : View> T.isInvisible(): Boolean = this.visibility == View.INVISIBLE

/**
 * 是否消失
 */
fun <T : View> T.isGone(): Boolean = this.visibility == View.GONE

/**
 * 记录View的点击事件戳
 */
private var <T : View> T.lastClickTime: Long
    get() = getTag(hashCode() + 1) as? Long ?: 0
    set(value) {
        setTag(hashCode() + 1, value)
    }

/**
 * 允许2次点击的间隔事件
 */
private var <T : View> T.delayTime: Long
    get() = getTag(hashCode() + 2) as? Long ?: 0
    set(value) {
        setTag(hashCode() + 2, value)
    }

private fun <T : View> T.canClick(): Boolean {
    var flag = false
    var now = System.currentTimeMillis()
    if (now - this.lastClickTime >= this.delayTime) {
        flag = true
        this.lastClickTime = now
    }
    return flag
}


