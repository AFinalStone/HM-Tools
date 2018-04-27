package com.hm.iou.tools;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hjy on 18/4/27.<br>
 */

public class AssetUtil {

    /**
     * 从asset文件里面读取文本内容
     *
     * @param assetFileName
     * @return
     */
    public static String readTextFromAssetFile(Context context, String assetFileName) {
        AssetManager manager = context.getAssets();
        try {
            InputStream inputStream = manager.open(assetFileName);
            int length = inputStream.available();
            byte[] buffer = new byte[length];
            inputStream.read(buffer);
            inputStream.close();
            String str = new String(buffer);
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}