package com.hm.iou.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.io.File;
import java.util.List;

/***
 * 系统工具类  获取  包管理器信息，SDcard信息，网络状况信息
 * @author SHI
 * 2016-2-25 16:39:55
 */
public class SystemUtil {

    /**
     * 获取当前应用版本号
     **/
    public static int getCurrentAppVersionCode(Context context) {
        // 获得包管理器，注意，整个android手机，共用一个包管理器
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo != null) {
            return packageInfo.versionCode;
        } else {
            return 0;
        }
    }

    /**
     * 根据包名启动App
     *
     * @param context
     */
    public static void launchApp(Context context, String packageName) {
        // 判断是否安装过App，安装过则启动，否则去市场下载
        if (isAppInstalled(context, packageName)) {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(packageName));
        } else {
            Toast.makeText(context, "当前手机没有安装此应用", Toast.LENGTH_SHORT).show();
        }
    }

//    /**
//     * 调用第三方浏览器打开
//     *
//     * @param context 设备上下文
//     * @param url     链接地址
//     */
//    public static void openWebBrowser(Context context, String url) {
//        Intent intent = new Intent();
//        intent.setData(Uri.parse(url));//Url 就是你要打开的网址
//        intent.setAction(Intent.ACTION_VIEW);
//        context.startActivity(intent); //启动浏览器
//    }

    /**
     * 调用第三方浏览器打开
     *
     * @param context
     * @param url     要浏览的资源地址
     */
    public static void openWebBrowser(Context context, String url) {
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
        // 官方解释 : Name of the component implementing an activity that can display the intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            final ComponentName componentName = intent.resolveActivity(context.getPackageManager());
            context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
        } else {
            Toast.makeText(context.getApplicationContext(), "请下载浏览器", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 检测某个应用是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 去市场下载页面
     */
    public static void goToMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
        }
    }

    /**
     * 获取当前应用版包名
     **/
    public static String getCurrentAppPackageName(Context context) {
        // 获得包管理器，注意，整个android手机，共用一个包管理器
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo != null) {
            return packageInfo.packageName;
        } else {
            return "";
        }
    }

    /**
     * 获取当前应用版本名
     **/
    public static String getCurrentAppVersionName(Context context) {
        // 获得包管理器，注意，整个android手机，共用一个包管理器
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo != null) {
            return packageInfo.versionName;
        } else {
            return "";
        }
    }

    /**
     * 获取当前应用API版本名
     **/
    public static int getCurrentOSVersion() {
        /*获取当前系统的android版本号*/
        int version = android.os.Build.VERSION.SDK_INT;
        return version;
    }


    /**
     * 获取SDK卡的根目录
     **/
    private static File getExternalStorageDirectory() {
        // 判断sd卡是否存在
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory();// 获取跟目录
        }
        return null;
    }

    /****
     * 返回系统默认Download文件夹下的File文件路径
     * @param fileName   文件名称
     * @return
     */
    public static String getDownloadFilePath(String fileName) {
        String downDirectory = null;
        String FolderName = Environment.DIRECTORY_DOWNLOADS;
        File pathSDcard = getExternalStorageDirectory();
        if (pathSDcard != null) {
            //创建文件夹
            if (createFolder(FolderName)) {
                downDirectory = pathSDcard.toString() + File.separator + FolderName
                        + File.separator + fileName;
            }
        }
        return downDirectory;
    }

    /****
     * 返回系统默认Download文件夹路径
     * @return
     */
    public static String getDownloadFilePath() {
        String downDirectory = null;
        String FolderName = Environment.DIRECTORY_DOWNLOADS;
        File pathSDcard = getExternalStorageDirectory();
        if (pathSDcard != null) {
            //创建文件夹
            if (createFolder(FolderName)) {
                downDirectory = pathSDcard.toString() + File.separator + FolderName;
            }
        }
        return downDirectory;
    }

    /*****
     * 创建文件夹
     * @param FolderName 文件夹名称
     * @return
     */
    public static boolean createFolder(String FolderName) {

        File pathSDcard = getExternalStorageDirectory();
        if (pathSDcard != null) {
            File file = new File(pathSDcard.toString() + File.separator + FolderName);

            if (!file.exists()) {// 目录不存在
                file.mkdirs();
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断指定名称的服务是否运行
     *
     * @param act  activity
     * @param name 服务的类的全名
     * @return
     */
    public static boolean isServiceRunning(Activity act, String name) {
        ActivityManager am = (ActivityManager) act.getSystemService(Context.ACTIVITY_SERVICE);
        // 返回正在运行的服务的信息列表
        List<RunningServiceInfo> infoList = am.getRunningServices(100); // 参数是指，最多返回多少个服务的信息
        for (RunningServiceInfo runningServiceInfo : infoList) {
            String className = runningServiceInfo.service.getClassName();
            if (className.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取手机IMEI
     *
     * @param context
     * @return
     */
    public static String getIMIE(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String deviceId = tm.getDeviceId();
        return deviceId;
    }

    /**
     * 获取手机mac地址
     *
     * @param context
     * @return
     */
    private static String getLocalMac(Context context) {
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info != null ? info.getMacAddress() : null;
    }

    // Android Id
    public static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(
                context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }

}













