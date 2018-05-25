package com.hm.iou.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 文件存储工具类
 *
 * @author AFinalStone
 * @time 2018/4/2 下午8:11
 */

public class FileUtil {

    /**
     *
     * getCacheDir()方法用于获取/data/data/<application package>/cache目录
     * getFilesDir()方法用于获取/data/data/<application package>/files目录
     * <p>
     * 应用程序在运行的过程中如果需要向手机上保存数据，一般是把数据保存在SDcard中的。
     * 大部分应用是直接在SDCard的根目录下创建一个文件夹，然后把数据保存在该文件夹中。
     * 这样当该应用被卸载后，这些数据还保留在SDCard中，留下了垃圾数据。
     * 如果你想让你的应用被卸载后，与该应用相关的数据也清除掉，该怎么办呢？
     * 通过Context.getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
     * 通过Context.getExternalCacheDir()方法可以获取到 SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
     * 如果使用上面的方法，当你的应用在被用户卸载后，SDCard/Android/data/你的应用的包名/ 这个目录下的所有文件都会被删除，不会留下垃圾信息。
     */


    /**
     * 获取外部SD卡的缓存路径，需要访问外部存储设备的权限
     *
     * @param context
     * @return
     */
    public static String getExternalCacheDirPath(Context context) {
        String cachePath = null;
        if (hasSdcard()) {
            cachePath = context.getExternalCacheDir().getPath();
        }
        return cachePath;
    }

    /**
     * 获取APP本身的缓存路径
     *
     * @param context
     * @return
     */
    public static String getCacheDirPath(Context context) {
        return context.getCacheDir().getPath();
    }

    /**
     * 保存Bitmap图片到目标文件
     *
     * @param bitmap
     * @param target
     * @return
     */
    public static boolean saveBitmapToTargetFile(Bitmap bitmap, File target) {
        try {
            FileOutputStream out = new FileOutputStream(target);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || !Environment.isExternalStorageRemovable();
    }

}
