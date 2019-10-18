package com.hm.iou.tools.demo

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

/**
 * @author : 借条管家-shilei
 * @version : 0.0.1
 * @Date : 2019-10-17 14:22
 * @E-Mail : afinalstone@foxmail.com
 */
class HMApplication : Application() {
    /**
     * 分包
     */
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}