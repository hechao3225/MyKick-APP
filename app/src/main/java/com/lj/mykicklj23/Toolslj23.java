package com.lj.mykicklj23;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by LJ on 2016/6/20.
 */
public class Toolslj23 {
    private Toolslj23() {
    }

    /**
     * 获取手机网络连接类型
     *
     * @param context
     * @return 0:无网络1:无线网络2:2G网络3:其他网络
     */
    public static int getNetworkTypelj23(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return 0;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        NetworkInfo netWorkInfo = info[i];
                        if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            return 1;
                        } else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            String extraInfo = netWorkInfo.getExtraInfo();
                            if ("cmwap".equalsIgnoreCase(extraInfo) || "cmwap:gsm".equalsIgnoreCase(extraInfo)) {
                                return 2;
                            }
                            return 3;
                        }
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 判断是否有可用网络
     *
     * @param context
     * @return
     */
    public static boolean hasNoNetworklj23(Context context) {
        return getNetworkTypelj23(context) == 0;
    }
}
