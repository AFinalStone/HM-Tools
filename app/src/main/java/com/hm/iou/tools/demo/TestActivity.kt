package com.hm.iou.tools.demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hm.iou.tools.kt.clickWithDuration
import com.hm.iou.tools.kt.startActivityForResult
import kotlinx.android.synthetic.main.activity_test1.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)

        btn_test1.clickWithDuration(1000) {
            println("click...click...")
            testClick(btn_test1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("onActivityResult")
    }

    fun testClick(v: View) {
//        startActivityForResult<TestActivity2>(100)

        var s: Long? = null

        println(s)
    }

    fun testClick2(v: View) {
        var list: ArrayList<String> = ArrayList<String>()
        list.add("aaa")
        list.add("bbb")

        var intList = ArrayList<Int>()
        intList.add(111)
        intList.add(222)
        startActivityForResult<TestActivity2>(100, "key1" to "value1", "key2" to "value2",
                "ss1" to list,
                "ss2" to intList,
                "ss3" to arrayOf("sss", "sss", "sss"))
    }

    fun testClick3(v: View) {
        val data = Bundle()
        data.putString("key1", "value1")
        data.putString("key2", "value2")
        startActivityForResult<TestActivity2>(100, data)
    }

}