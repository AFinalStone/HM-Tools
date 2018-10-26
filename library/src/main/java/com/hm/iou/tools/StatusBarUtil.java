package com.hm.iou.tools;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.Window;

/**
 * Created by syl on 2018/10/26.
 */

public class StatusBarUtil {

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
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
