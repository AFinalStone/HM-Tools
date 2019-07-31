package com.hm.iou.tools.demo

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hm.iou.tools.kt.extraDelegate
import com.hm.iou.tools.kt.getValue
import com.hm.iou.tools.kt.putValue

class TestActivity2 : AppCompatActivity() {

    private var key1: String? = null
    private val key2: String? by extraDelegate("key2", null)
    private var key3: String? by extraDelegate("key3", "null extra")

    private var key4: Array<String>? = null

    private var s1: ArrayList<String>? by extraDelegate("ss1", null)
    private val s2: ArrayList<Int>? by extraDelegate("ss2", null)
    private var s3: Array<String>? by extraDelegate("ss3", null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)
        key1 = intent.getStringExtra("key1")
        if (savedInstanceState != null) {
            key1 = savedInstanceState.getValue("key1")
            key3 = savedInstanceState.getValue("key3")
            key4 = savedInstanceState.getValue("key4")
        }


        println("key1 = $key1")
        println("key2 = $key2")
        println("key3 = $key3")
        println("key4 = $key4")

        println(s1)
        println(s2)
        println(s3?.get(0))
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putValue("key1", "我是测试key01")
                ?.putValue("key3", "我是测试key03")
                ?.putValue("key4", "我是测试kye04")

    }

    fun testClick(v: View) {
        setResult(Activity.RESULT_OK)
        finish()
    }

}