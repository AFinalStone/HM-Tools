package com.hm.iou.tools;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hjy on 18/4/27.<br>
 */

public class KeyboardUtil {

    /**
     * 显示软键盘的状态
     */
    public static void openKeyboard(final Activity activity) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    InputMethodManager imm = (InputMethodManager) activity.getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInputFromInputMethod(activity.getWindow().getDecorView().getWindowToken(), 0);
                } catch (Exception e) {

                }
            }
        }, 300);
    }

    /**
     * 显示键盘
     */
    public static void showKeyboard(final View view) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) view.getContext().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
            }
        }, 300);
    }

    /**
     * 隐藏键盘
     *
     * @param activity
     * @return
     */
    public static boolean hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            return imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
        return false;
    }

}