package com.hm.iou.tools;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * 剪切板工具
 *
 * @author AFinalStone
 * @time 2018/4/18 上午9:59
 */
public class ClipUtil {


    private Context mContext;
    private static ClipUtil clipUtil;

    public ClipUtil(Context context) {
        this.mContext = context;
    }

    public static ClipUtil getInstance(Context context) {
        if (clipUtil == null) {
            clipUtil = new ClipUtil(context);
        }
        return clipUtil;
    }

    public void getTextFromClip() {
        ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        //判断剪切版时候有内容
        if (!clipboardManager.hasPrimaryClip())
            return;
        ClipData clipData = clipboardManager.getPrimaryClip();
        //获取 ClipDescription
        ClipDescription clipDescription = clipboardManager.getPrimaryClipDescription();
        //获取 lable
        String lable = clipDescription.getLabel().toString();
        //获取 text
        String text = clipData.getItemAt(0).getText().toString();
    }

    public void putTextIntoClip(String text) {
        ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        //创建ClipData对象
        ClipData clipData = ClipData.newPlainText("LabelText", text);
        //添加ClipData对象到剪切板中
        clipboardManager.setPrimaryClip(clipData);
    }


//    //创建一个包含 htmlText 的 ClipData
//    //一般在浏览器中对网页进行拷贝的时候会调用此方法,其中 htmlText 是包含 HTML 标签的字符串
//    public static ClipData newHtmlText(CharSequence label, CharSequence text, String htmlText)
//
//    //创建一个包含 Intent 的 ClipData
//    public static ClipData newIntent(CharSequence label, Intent intent)
//
//    //创建一个包含 Uri 的 ClipData，MimeType 会根据 Uri 进行修改
//    public static ClipData newUri(ContentResolver resolver, CharSequence label, Uri uri)
//
//    //与 newUri 相对应，但是并不会根据 Uri 修改 MimeType
//    public static ClipData newRawUri(CharSequence label, Uri uri)


}
