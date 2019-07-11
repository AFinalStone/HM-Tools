package com.hm.iou.tools.demo

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hm.iou.tools.kt.extraDelegate
import com.hm.iou.tools.kt.unPackData

class TestActivity2 : AppCompatActivity() {

    private var key1: String? by extraDelegate("key1", "")
    private val key2: String? by extraDelegate("key2", null)
    private var key3: String? by extraDelegate("key3", "null extra")

    private var kye4: Array<String>? = null

    private var s1: ArrayList<String>? by extraDelegate("ss1", null)
    private val s2: ArrayList<Int>? by extraDelegate("ss2", null)
    private var s3: Array<String>? by extraDelegate("ss3", null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)
        if (savedInstanceState != null) {
            key1 = unPackData(savedInstanceState, "key1")
            kye4 = unPackData(savedInstanceState, "key4")
        }

        println("key1 = $key1")
        println("key2 = $key2")
        println("key3 = $key3")

        println(s1)
        println(s2)
        println(s3?.get(0))
    }

    fun testClick(v: View) {
        setResult(Activity.RESULT_OK)
        finish()
    }

}