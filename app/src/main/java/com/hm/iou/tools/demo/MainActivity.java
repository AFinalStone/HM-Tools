package com.hm.iou.tools.demo;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.hm.iou.tools.MoneyFormatUtil;
import com.hm.iou.tools.ToastUtil;

public class MainActivity extends AppCompatActivity {

    EditText mEtAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMessage(MainActivity.this, "测试显示toast文本");
            }
        });

        findViewById(R.id.btn_test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showStatusView(MainActivity.this, "加载成功", true);
            }
        });

        mEtAmount = findViewById(R.id.et_test_upper);
        findViewById(R.id.btn_test3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String amount = mEtAmount.getText().toString();
                    String upper = MoneyFormatUtil.toChineseCharI(amount);
                    System.out.println(upper);
                    ToastUtil.showMessage(MainActivity.this, upper);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
