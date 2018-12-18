package com.hm.iou.tools.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.hm.iou.tools.ImageLoader;
import com.hm.iou.tools.KeyboardUtil;
import com.hm.iou.tools.MoneyFormatUtil;
import com.hm.iou.tools.StatusBarUtil;
import com.hm.iou.tools.ToastUtil;
import com.hm.iou.tools.ViewConcurrencyUtil;

public class MainActivity extends AppCompatActivity {

    EditText mEtAmount;
    EditText mEtFToY;

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

        ImageView imageView = (ImageView) findViewById(R.id.iv_tools);
        ImageLoader.getInstance(this).displayImage("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2118739199,3378602431&fm=27&gp=0.jpg", imageView);

        mEtFToY = findViewById(R.id.et_test_fToY);
        findViewById(R.id.btn_test_fToY).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String amount = mEtFToY.getText().toString();
                    long fen = Long.parseLong(amount);
                    String yuan = null;
                    try {
                        yuan = MoneyFormatUtil.changeF2Y(fen);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(yuan);
                    ToastUtil.showMessage(MainActivity.this, yuan);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_test_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ViewConcurrencyUtil.isFastClicks()) {
                    ToastUtil.showMessage(MainActivity.this, "onClick:被点击");
                }
            }
        });
        findViewById(R.id.btn_contacts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContactsActivity.class));
            }
        });
        findViewById(R.id.btn_showKeyboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardUtil.toggleKeyboard(MainActivity.this);
            }
        });
        findViewById(R.id.btn_getStatusBarHeight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int height = StatusBarUtil.getStatusBarHeight(getWindow());
                ToastUtil.showMessage(MainActivity.this, "getWindow状态栏高度==" + height);
                height = StatusBarUtil.getStatusBarHeight(MainActivity.this);
                ToastUtil.showMessage(MainActivity.this, "getRes状态栏高度==" + height);
            }
        });

    }
}
