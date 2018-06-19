package com.hm.iou.tools;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hjy on 18/4/27.<br>
 */
public class ToastUtil {

    private static Toast mToast;

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    private static Runnable mPendingDismissRunnable = new Runnable() {
        @Override
        public void run() {
            if (mToast != null) {
                mToast.cancel();
                mToast = null;
            }
        }
    };

    public static void showMessage(Context context, CharSequence text) {
        showMessage(context, text, 2500);
    }

    public static void showMessage(Context context, int textResId) {
        showMessage(context, textResId, 2500);
    }

    /**
     * 显示toast
     *
     * @param context
     * @param textResId 字符串资源id
     * @param duration  持续时间, 毫秒
     */
    public static void showMessage(Context context, int textResId, int duration) {
        if (context == null)
            return;
        showMessage(context, context.getString(textResId), duration);
    }

    /**
     * 显示toast
     *
     * @param context
     * @param text
     * @param duration 持续时间, 毫秒
     */
    public static void showMessage(Context context, CharSequence text, int duration) {
        if (context == null)
            return;
        mHandler.removeCallbacks(mPendingDismissRunnable);
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        mHandler.postDelayed(mPendingDismissRunnable, duration);
        mToast.show();
    }

    /**
     * 方法名： showToast
     * 防止多次弹框
     *
     * @param context
     * @param text
     * @param duration
     */
    private static void showMessage(Context context, String text, int duration) {
        if (TextUtils.isEmpty(text)) return;
        if (null != mToast) {
            mToast.setText(text);
            mToast.setGravity(Gravity.BOTTOM, 0, 100);
        } else {
            mToast = Toast.makeText(context, text, duration);
            mToast.setGravity(Gravity.BOTTOM, 0, 100);
        }
        mToast.show();
    }

    /**
     * 显示带有图片的toast
     *
     * @param context
     * @param message  toast文本
     * @param imgResId
     * @return
     */
    public static Toast showMessageWithImage(Context context, String message, int imgResId) {
        View view = LayoutInflater.from(context).inflate(R.layout.tools_layout_toast_custom, null);
        TextView tv = view.findViewById(R.id.tv_toast_text);
        tv.setText(TextUtils.isEmpty(message) ? "" : message);
        ImageView iv = view.findViewById(R.id.iv_toast_icon);
        iv.setImageResource(imgResId);
        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }

    /**
     * 显示带有成功或者失败图片的toast
     *
     * @param context
     * @param message   toast文本
     * @param isSuccess 成功或者失败
     */
    public static void showStatusView(Context context, String message, boolean isSuccess) {
        showMessageWithImage(context, message, isSuccess ? R.mipmap.tools_ic_toast_success : R.mipmap.tools_ic_toast_error);
    }
}