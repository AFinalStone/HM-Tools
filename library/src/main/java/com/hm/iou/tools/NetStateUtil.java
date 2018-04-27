package com.hm.iou.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by AFinalStone on 2016/8/4.
 */
public class NetStateUtil {

    /**
     * 检查WIFI是否连接
     *
     * @return 如果连接了返回true，否则返回false
     * @author Ysjian
     * @date 2014-5-9
     */
    public static boolean isWifiConnected(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiInfo != null && wifiInfo.isConnected();
    }

    /**
     * 检查手机网络(4G/3G/2G)是否连接
     *
     * @return 如果连接了返回true，否则返回false
     * @author Ysjian
     * @date 2014-5-9
     */
    public static boolean isMobileNetworkConnected(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetworkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return mobileNetworkInfo != null && mobileNetworkInfo.isConnected();
    }

    /**
     * 检查是否有可用网络
     *
     * @return 存在WIFI和手机数据任意可用网络返回true，否则返回false
     * @author Ysjian
     * @date 2014-5-9
     */
    public static boolean isNetworkConnected(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /****
     * 获取当前网络连接类型
     * @param mContext
     * @return
     *
     */
    public static int getConnectedType(Context mContext) {
        if (mContext != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

}
