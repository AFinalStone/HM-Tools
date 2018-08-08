package com.hm.iou.tools;


import android.view.View;

/**
 * 防止按钮并发
 * Created by sll on 2017/6/8.
 */

public class ViewConcurrencyUtil {

    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    /**
     * 是否是快速点击
     *
     * @param view
     * @return
     */
    public static boolean isFastClick(View view) {
        boolean flag = false;
        Long t = (Long) view.getTag(R.id.iv_toast_icon);
        if (t != null && (System.currentTimeMillis() - t) < MIN_CLICK_DELAY_TIME) {
            flag = true;
        } else {
            view.setTag(R.id.iv_toast_icon, System.currentTimeMillis());
        }
        return flag;
    }

}
