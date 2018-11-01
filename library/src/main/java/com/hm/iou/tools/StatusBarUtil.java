package com.hm.iou.tools;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.Window;

import java.lang.reflect.Field;

/**
 * Created by syl on 2018/10/26.
 */

public class StatusBarUtil {

    public static int getStatusBarHeight(Context context) {
        int h = getStatusBar1(context);
        if (h == 0) {
            h = getStatusBar2(context);
        }
        return h;
    }

    private static int getStatusBar1(Context context){
        int result = 0;
        try {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static int getStatusBar2(Context context) {
        try {
            Class c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            int statusBarHeight = context.getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getStatusBarHeight(Window window) {
        View view = window.getDecorView();
        // 获取状态栏高度
        Rect frame = new Rect();
        // 测量屏幕宽和高
        view.getWindowVisibleDisplayFrame(frame);
        int result = frame.top;
        return result;
    }
}
